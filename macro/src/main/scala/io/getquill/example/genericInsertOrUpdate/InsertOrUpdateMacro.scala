package io.getquill.example.genericInsertOrUpdate

import scala.reflect.macros.whitebox.{Context => MacroContext}

class InsertOrUpdateMacro(val c: MacroContext) {

  import c.universe._

  def insertOrUpdate[T](entity: Tree, filter: Tree)(implicit t: WeakTypeTag[T]): Tree =
    q"""
      import ${c.prefix}._
      val updateQuery = ${c.prefix}.quote {
        ${c.prefix}.query[$t].filter($filter).update(lift($entity))
      }
      val insertQuery = quote {
        query[$t].insert(lift($entity))
      }
      run(${c.prefix}.query[$t].filter($filter)).size match {
          case 1 => run(updateQuery)
          case _ => run(insertQuery)
      }
      ()
    """
}
