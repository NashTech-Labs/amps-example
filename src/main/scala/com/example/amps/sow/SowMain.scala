package com.example.amps.sow

import com.example.amps.haclient.AmpsHAClient
import com.example.utils.Constants.{CLIENT_NAME, SERVER_IPS, SOW_MESSAGE, SOW_TOPIC_NAME}
import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

object SowMain extends App {

  val ampsHAClient = new AmpsHAClient
  val hAClient = ampsHAClient.getConnection(CLIENT_NAME, SERVER_IPS)

  println(">>>>>>>>>>>>>>>>>>Publishing message>>>>>>>>>>>>>>>>>")
  Try(hAClient.publish(SOW_TOPIC_NAME, SOW_MESSAGE)) match {
    case Success(sequenceNumber) => println(s"Sequence Number : $sequenceNumber")
    case Failure(error) => println(error.getMessage)
  }
  println(">>>>>>>>>>>>>>>>>>Subscribing to sow topic>>>>>>>>>>>")
  val messageStream = Try(hAClient.sowAndSubscribe(SOW_TOPIC_NAME))

  messageStream match {
    case Success(messageStream) => messageStream.iterator().asScala.foreach(message => println(message.getData))
    case Failure(error) => println(error.getMessage)
  }
}
