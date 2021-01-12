package models

import scalikejdbc._

case class Countrylanguage(
  countrycode: String,
  language: String,
  isofficial: String,
  percentage: BigDecimal) {

  def save()(implicit session: DBSession = Countrylanguage.autoSession): Countrylanguage = Countrylanguage.save(this)(session)

  def destroy()(implicit session: DBSession = Countrylanguage.autoSession): Int = Countrylanguage.destroy(this)(session)

}


object Countrylanguage extends SQLSyntaxSupport[Countrylanguage] {

  override val tableName = "countrylanguage"

  override val columns = Seq("CountryCode", "Language", "IsOfficial", "Percentage")

  def apply(c: SyntaxProvider[Countrylanguage])(rs: WrappedResultSet): Countrylanguage = autoConstruct(rs, c)
  def apply(c: ResultName[Countrylanguage])(rs: WrappedResultSet): Countrylanguage = autoConstruct(rs, c)

  val c = Countrylanguage.syntax("c")

  override val autoSession = AutoSession

  def find(countrycode: String, language: String)(implicit session: DBSession = autoSession): Option[Countrylanguage] = {
    withSQL {
      select.from(Countrylanguage as c).where.eq(c.countrycode, countrycode).and.eq(c.language, language)
    }.map(Countrylanguage(c.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Countrylanguage] = {
    withSQL(select.from(Countrylanguage as c)).map(Countrylanguage(c.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Countrylanguage as c)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Countrylanguage] = {
    withSQL {
      select.from(Countrylanguage as c).where.append(where)
    }.map(Countrylanguage(c.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Countrylanguage] = {
    withSQL {
      select.from(Countrylanguage as c).where.append(where)
    }.map(Countrylanguage(c.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Countrylanguage as c).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    countrycode: String,
    language: String,
    isofficial: String,
    percentage: BigDecimal)(implicit session: DBSession = autoSession): Countrylanguage = {
    withSQL {
      insert.into(Countrylanguage).namedValues(
        column.countrycode -> countrycode,
        column.language -> language,
        column.isofficial -> isofficial,
        column.percentage -> percentage
      )
    }.update.apply()

    Countrylanguage(
      countrycode = countrycode,
      language = language,
      isofficial = isofficial,
      percentage = percentage)
  }

  def batchInsert(entities: collection.Seq[Countrylanguage])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        Symbol("countrycode") -> entity.countrycode,
        Symbol("language") -> entity.language,
        Symbol("isofficial") -> entity.isofficial,
        Symbol("percentage") -> entity.percentage))
    SQL("""insert into countrylanguage(
      CountryCode,
      Language,
      IsOfficial,
      Percentage
    ) values (
      {countrycode},
      {language},
      {isofficial},
      {percentage}
    )""").batchByName(params.toSeq: _*).apply[List]()
  }

  def save(entity: Countrylanguage)(implicit session: DBSession = autoSession): Countrylanguage = {
    withSQL {
      update(Countrylanguage).set(
        column.countrycode -> entity.countrycode,
        column.language -> entity.language,
        column.isofficial -> entity.isofficial,
        column.percentage -> entity.percentage
      ).where.eq(column.countrycode, entity.countrycode).and.eq(column.language, entity.language)
    }.update.apply()
    entity
  }

  def destroy(entity: Countrylanguage)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(Countrylanguage).where.eq(column.countrycode, entity.countrycode).and.eq(column.language, entity.language) }.update.apply()
  }

}
