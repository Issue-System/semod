package ru.kvb74.semod.system.system

import ru.kvb74.semod.system.meta.element.{SystemOtherElement, SystemOtherElementRelationships}
import ru.kvb74.semod.system.meta.layer.System


case class SystemOther(
                      name: String,
                      desc: String = ""
                    ) extends System
  with SystemOtherElement {

  case class SystemOtherRelationships(
                                     override private[semod] implicit val tt: SystemOther = SystemOther.this
                                   ) extends SystemOtherElementRelationships[SystemOther]

  val rel: SystemOtherRelationships = SystemOtherRelationships()

  _registerPrefix("SOther")
}
