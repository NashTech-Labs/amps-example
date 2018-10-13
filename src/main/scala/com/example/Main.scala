package com.example

import com.example.amps.{AmpsHAClient, AmpsHandler}
import com.example.utils.Constants._

import scala.util.{Failure, Success}

object Main extends App {

  val ampsHAClient = new AmpsHAClient
  val hAClient = ampsHAClient.getConnection(CLIENT_NAME, SERVER_IPS)
  val ampsHandler = new AmpsHandler(hAClient)

  println(">>>>>>>>>>>>>>>>>>>Starting Subscriber<<<<<<<<<<<<<<<<")
  val subscriber = ampsHandler.subscribe(TOPIC_NAME)

  println(">>>>>>>>>>>>>>>>>>>Publishing Message<<<<<<<<<<<<<<<<")
  ampsHandler.publish(TOPIC_NAME, MESSAGE) match {
    case Success(sequenceNumber) => println(s"Sequence Number : $sequenceNumber")
    case Failure(error) => println(error.getMessage)
  }

  println(">>>>>>>>>>>>>>>>>>>Got the Message<<<<<<<<<<<<<<<<")
  subscriber match {
    case Success(messageStream) => messageStream.foreach(message => println(message.getData))
    case Failure(error) => println(error.getMessage)
  }
}
