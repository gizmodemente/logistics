package com.fdelosrios.logistics.containers.infrastructure.entities

import java.time.LocalDateTime

import akka.Done
import com.fdelosrios.logistics.containers.domain.model.Container
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import play.api.libs.json.{Format, Json}

class ContainersEntity extends PersistentEntity {

  override type Command = ContainerCommands[_]
  override type Event = LogisticsEvent
  override type State = ContainerState

  /**
    * The initial state. This is used if there is no snapshotted state to be found.
    */
  override def initialState: ContainerState = ContainerState(None, None, ContainerStatus.UNREGISTERED.toString, LocalDateTime.now.toString)

  /**
    * An entity can define different behaviours for different states, so the behaviour
    * is a function of the current state to a set of actions.
    */
  override def behavior: Behavior = {
    case state if state.status.equals(ContainerStatus.UNREGISTERED) => unregisteredContainer
    case state if state.status.equals(ContainerStatus.EMPTY) => unregisteredContainer
  }

  val unregisteredContainer =
    Actions().onReadOnlyCommand[GetContainer, Container] {
      case (GetContainer(_), ctx, state) =>
        println(s"aqui llega $entityId")
        ctx.reply(Container(entityId, state.status, "0"))
    }
}


/**
  * This interface defines all the events that the LogisticsEntity supports.
  */
sealed trait LogisticsEvent extends AggregateEvent[LogisticsEvent] {
  def aggregateTag: AggregateEventTag[LogisticsEvent] = LogisticsEvent.Tag
}

object LogisticsEvent {
  val Tag: AggregateEventTag[LogisticsEvent] = AggregateEventTag[LogisticsEvent]
}

/**
  * An event that represents a change in greeting message.
  */
case class GreetingMessageChanged(message: String) extends LogisticsEvent

object GreetingMessageChanged {

  /**
    * Format for the greeting message changed event.
    *
    * Events get stored and loaded from the database, hence a JSON format
    * needs to be declared so that they can be serialized and deserialized.
    */
  implicit val format: Format[GreetingMessageChanged] = Json.format
}