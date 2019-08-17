package com.fdelosrios.logistics.containers.infrastructure.entities

import akka.Done
import com.fdelosrios.logistics.containers.domain.model.Container
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import play.api.libs.json.{Format, Json}

sealed trait ContainerCommands[R] extends ReplyType[R]

case class GetContainer(id: String) extends ContainerCommands[Container]

object GetContainer {
  implicit val format: Format[GetContainer] = Json.format[GetContainer]
}
