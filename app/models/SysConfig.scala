package models

import scalikejdbc._
import java.time.{OffsetDateTime}

case class SysConfig(
  variable: String,
  value: Option[String] = None,
  setTime: Option[OffsetDateTime] = None,
  setBy: Option[String] = None) {

  def save()(implicit session: DBSession = SysConfig.autoSession): SysConfig = SysConfig.save(this)(session)

  def destroy()(implicit session: DBSession = SysConfig.autoSession): Int = SysConfig.destroy(this)(session)

}


object SysConfig extends SQLSyntaxSupport[SysConfig] {

  override val tableName = "sys_config"

  override val columns = Seq("variable", "value", "set_time", "set_by")

  def apply(sc: SyntaxProvider[SysConfig])(rs: WrappedResultSet): SysConfig = autoConstruct(rs, sc)
  def apply(sc: ResultName[SysConfig])(rs: WrappedResultSet): SysConfig = autoConstruct(rs, sc)

  val sc = SysConfig.syntax("sc")

  override val autoSession = AutoSession

  def find(variable: String)(implicit session: DBSession = autoSession): Option[SysConfig] = {
    withSQL {
      select.from(SysConfig as sc).where.eq(sc.variable, variable)
    }.map(SysConfig(sc.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[SysConfig] = {
    withSQL(select.from(SysConfig as sc)).map(SysConfig(sc.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(SysConfig as sc)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[SysConfig] = {
    withSQL {
      select.from(SysConfig as sc).where.append(where)
    }.map(SysConfig(sc.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[SysConfig] = {
    withSQL {
      select.from(SysConfig as sc).where.append(where)
    }.map(SysConfig(sc.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(SysConfig as sc).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    variable: String,
    value: Option[String] = None,
    setTime: Option[OffsetDateTime] = None,
    setBy: Option[String] = None)(implicit session: DBSession = autoSession): SysConfig = {
    withSQL {
      insert.into(SysConfig).namedValues(
        column.variable -> variable,
        column.value -> value,
        column.setTime -> setTime,
        column.setBy -> setBy
      )
    }.update.apply()

    SysConfig(
      variable = variable,
      value = value,
      setTime = setTime,
      setBy = setBy)
  }

  def batchInsert(entities: collection.Seq[SysConfig])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        Symbol("variable") -> entity.variable,
        Symbol("value") -> entity.value,
        Symbol("setTime") -> entity.setTime,
        Symbol("setBy") -> entity.setBy))
    SQL("""insert into sys_config(
      variable,
      value,
      set_time,
      set_by
    ) values (
      {variable},
      {value},
      {setTime},
      {setBy}
    )""").batchByName(params.toSeq: _*).apply[List]()
  }

  def save(entity: SysConfig)(implicit session: DBSession = autoSession): SysConfig = {
    withSQL {
      update(SysConfig).set(
        column.variable -> entity.variable,
        column.value -> entity.value,
        column.setTime -> entity.setTime,
        column.setBy -> entity.setBy
      ).where.eq(column.variable, entity.variable)
    }.update.apply()
    entity
  }

  def destroy(entity: SysConfig)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(SysConfig).where.eq(column.variable, entity.variable) }.update.apply()
  }

}
