package models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._


class CountrylanguageSpec extends Specification {

  "Countrylanguage" should {

    val c = Countrylanguage.syntax("c")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Countrylanguage.find("MyString", "MyString")
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = Countrylanguage.findBy(sqls.eq(c.countrycode, "MyString"))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Countrylanguage.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Countrylanguage.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = Countrylanguage.findAllBy(sqls.eq(c.countrycode, "MyString"))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Countrylanguage.countBy(sqls.eq(c.countrycode, "MyString"))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Countrylanguage.create(countrycode = "MyString", language = "MyString", isofficial = "MyString", percentage = new java.math.BigDecimal("1"))
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Countrylanguage.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Countrylanguage.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Countrylanguage.findAll().head
      val deleted = Countrylanguage.destroy(entity) == 1
      deleted should beTrue
      val shouldBeNone = Countrylanguage.find("MyString", "MyString")
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = Countrylanguage.findAll()
      entities.foreach(e => Countrylanguage.destroy(e))
      val batchInserted = Countrylanguage.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}
