package com.fdelosrios.logistics.api

import com.fdelosrios.logistics.containers.domain.ContainersService
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service}

trait ContainerServiceApi extends ContainersService {
  override final def descriptor: Descriptor = {
    import Service._
    // @formatter:off
    named("logistics")
      .withCalls(
        pathCall(ContainerServiceApi.GET_CONTAINER, getContainer _),
        pathCall(ContainerServiceApi.REGISTER_CONTAINER, registerContainer _),
        pathCall(ContainerServiceApi.UPDATE_CONTAINER, updateContainer _),
        pathCall(ContainerServiceApi.FILL_CONTAINER, fillContainer _),
        pathCall(ContainerServiceApi.EMPTY_CONTAINER, emptyContainer _)
      )
//      .withTopics(
//        topic(LogisticsService.TOPIC_NAME, greetingsTopic _)
//          // Kafka partitions messages, messages within the same partition will
//          // be delivered in order, to ensure that all messages for the same user
//          // go to the same partition (and hence are delivered in order with respect
//          // to that user), we configure a partition key strategy that extracts the
//          // name as the partition key.
//          .addProperty(
//            KafkaProperties.partitionKeyStrategy,
//            PartitionKeyStrategy[GreetingMessageChanged](_.name)
//          )
//      )
      .withAutoAcl(true)
    // @formatter:on
  }
}

object ContainerServiceApi {
  val GET_CONTAINER = "/api/v1/container/:id"
  val REGISTER_CONTAINER = "/api/v1/container"
  val UPDATE_CONTAINER = "/api/v1/container/:id"
  val FILL_CONTAINER = "/api/v1/container/:id/load"
  val EMPTY_CONTAINER = "/api/v1/container/:id/unload"
}