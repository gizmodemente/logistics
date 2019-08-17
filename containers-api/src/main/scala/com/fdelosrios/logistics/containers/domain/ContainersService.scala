package com.fdelosrios.logistics.containers.domain

import akka.{Done, NotUsed}
import com.fdelosrios.logistics.containers.domain.model.{Container, Load}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

object ContainersService  {
  val TOPIC_NAME = "greetings"
}

/**
  * The logistics service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LogisticsService.
  */
trait ContainersService extends Service {


  def getContainer(id: String): ServiceCall[NotUsed, Container]
  def updateContainer(id: String): ServiceCall[NotUsed, Container]
  def registerContainer(id: String): ServiceCall[Container,Done]
  def fillContainer(id: String): ServiceCall[Load, Done]
  def emptyContainer(id: String): ServiceCall[Load, Done]

//  def greetingsTopic(): Topic[GreetingMessageChanged]
}
