package com.pluswhale

import akka.actor.typed.ActorRef
import akka.io.Tcp.Event

// a single bank account
class PersistentBankAccount {
  /*

 -fault tolerance
 -auditing
   */

  // command = messages
  sealed trait Command
  case class CreateBankAccount(user: String, currency: String, initialBalance: Double, replyTo: ActorRef[Response]) extends Command
  case class UpdateBalance(id: String, currency: String, amount: Double /* can't be negative */, replyTo: ActorRef[Response]) extends Command
  case class GetBankAccount(id: String, replyTo: ActorRef[Response]) extends Command
  // events = persist to Cassandra
  trait event
  case class BanckAccountCreated(bankAccount: BankAccount) extends Event

  //state
  case class BankAccount(id: String, user: String, currency: String, balance: Double)
  //response

  sealed trait Response


}
