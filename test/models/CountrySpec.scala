package models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._


class CountrySpec extends Specification {

  "Country" should {

    val c = Country.syntax("c")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Country.find("MyString")
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = Country.findBy(sqls.eq(c.code, "MyString"))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Country.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Country.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = Country.findAllBy(sqls.eq(c.code, "MyString"))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Country.countBy(sqls.eq(c.code, "MyString"))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Country.create(code = "MyString", name = "MyString", continent = "MyString", region = "MyString", surfacearea = new java.math.BigDecimal("1"), population = 123, localname = "MyString", governmentform = "MyString", code2 = "MyString")
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Country.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Country.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Country.findAll().head
      val deleted = Country.destroy(entity) == 1
      deleted should beTrue
      val shouldBeNone = Country.find("MyString")
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = Country.findAll()
      entities.foreach(e => Country.destroy(e))
      val batchInserted = Country.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}
