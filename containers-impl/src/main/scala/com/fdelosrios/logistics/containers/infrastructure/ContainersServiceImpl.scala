package com.fdelosrios.logistics.containers.infrastructure

import akka.{Done, NotUsed}
import com.fdelosrios.logistics.api.ContainerServiceApi
import com.fdelosrios.logistics.containers.domain.model.{Container, Load}
import com.fdelosrios.logistics.containers.infrastructure.entities.{ContainersEntity, GetContainer}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry

/**
  * Implementation of the LogisticsService.
  */
class ContainersServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends ContainerServiceApi {

  override def getContainer(id: String): ServiceCall[NotUsed, Container] = ServiceCall { _ =>
    println(s"Al servicio $id")
    ref(id).ask(GetContainer(id))
  }

  override def updateContainer(id: String): ServiceCall[NotUsed, Container] = ???

  override def registerContainer(id: String): ServiceCall[Container, Done] = ???

  override def fillContainer(id: String): ServiceCall[Load, Done] = ???

  override def emptyContainer(id: String): ServiceCall[Load, Done] = ???

  private def ref(id: String) = persistentEntityRegistry.refFor[ContainersEntity](id)
}
