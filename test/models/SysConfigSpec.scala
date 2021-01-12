package models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._
import java.time.{OffsetDateTime}


class SysConfigSpec extends Specification {

  "SysConfig" should {

    val sc = SysConfig.syntax("sc")

    "find by primary keys" in new AutoRollback {
      val maybeFound = SysConfig.find("MyString")
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = SysConfig.findBy(sqls.eq(sc.variable, "MyString"))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = SysConfig.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = SysConfig.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = SysConfig.findAllBy(sqls.eq(sc.variable, "MyString"))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = SysConfig.countBy(sqls.eq(sc.variable, "MyString"))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = SysConfig.create(variable = "MyString")
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = SysConfig.findAll().head
      // TODO modify something
      val modified = entity
      val updated = SysConfig.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = SysConfig.findAll().head
      val deleted = SysConfig.destroy(entity) == 1
      deleted should beTrue
      val shouldBeNone = SysConfig.find("MyString")
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = SysConfig.findAll()
      entities.foreach(e => SysConfig.destroy(e))
      val batchInserted = SysConfig.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}
