package com.fdelosrios.logistics.containers.infrastructure

import com.fdelosrios.logistics.containers.domain.model.Container
import com.fdelosrios.logistics.containers.infrastructure.entities.{ContainerState, GetContainer, GreetingMessageChanged}
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}

import scala.collection.immutable.Seq

/**
  * Akka serialization, used by both persistence and remoting, needs to have
  * serializers registered for every type serialized or deserialized. While it's
  * possible to use any serializer you want for Akka messages, out of the box
  * Lagom provides support for JSON, via this registry abstraction.
  *
  * The serializers are registered here, and then provided to Lagom in the
  * application loader.
  */
object ContainersSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[GreetingMessageChanged],
    JsonSerializer[ContainerState],
    JsonSerializer[Container],
    JsonSerializer[GetContainer]
  )
}
