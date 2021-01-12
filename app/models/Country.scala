package models

import scalikejdbc._

case class Country(
  code: String,
  name: String,
  continent: String,
  region: String,
  surfacearea: BigDecimal,
  indepyear: Option[Short] = None,
  population: Int,
  lifeexpectancy: Option[BigDecimal] = None,
  gnp: Option[BigDecimal] = None,
  gnpold: Option[BigDecimal] = None,
  localname: String,
  governmentform: String,
  headofstate: Option[String] = None,
  capital: Option[Int] = None,
  code2: String) {

  def save()(implicit session: DBSession = Country.autoSession): Country = Country.save(this)(session)

  def destroy()(implicit session: DBSession = Country.autoSession): Int = Country.destroy(this)(session)

}


object Country extends SQLSyntaxSupport[Country] {

  override val tableName = "country"

  override val columns = Seq("Code", "Name", "Continent", "Region", "SurfaceArea", "IndepYear", "Population", "LifeExpectancy", "GNP", "GNPOld", "LocalName", "GovernmentForm", "HeadOfState", "Capital", "Code2")

  def apply(c: SyntaxProvider[Country])(rs: WrappedResultSet): Country = autoConstruct(rs, c)
  def apply(c: ResultName[Country])(rs: WrappedResultSet): Country = autoConstruct(rs, c)

  val c = Country.syntax("c")

  override val autoSession = AutoSession

  def find(code: String)(implicit session: DBSession = autoSession): Option[Country] = {
    withSQL {
      select.from(Country as c).where.eq(c.code, code)
    }.map(Country(c.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Country] = {
    withSQL(select.from(Country as c)).map(Country(c.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Country as c)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Country] = {
    withSQL {
      select.from(Country as c).where.append(where)
    }.map(Country(c.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Country] = {
    withSQL {
      select.from(Country as c).where.append(where)
    }.map(Country(c.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Country as c).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    code: String,
    name: String,
    continent: String,
    region: String,
    surfacearea: BigDecimal,
    indepyear: Option[Short] = None,
    population: Int,
    lifeexpectancy: Option[BigDecimal] = None,
    gnp: Option[BigDecimal] = None,
    gnpold: Option[BigDecimal] = None,
    localname: String,
    governmentform: String,
    headofstate: Option[String] = None,
    capital: Option[Int] = None,
    code2: String)(implicit session: DBSession = autoSession): Country = {
    withSQL {
      insert.into(Country).namedValues(
        column.code -> code,
        column.name -> name,
        column.continent -> continent,
        column.region -> region,
        column.surfacearea -> surfacearea,
        column.indepyear -> indepyear,
        column.population -> population,
        column.lifeexpectancy -> lifeexpectancy,
        column.gnp -> gnp,
        column.gnpold -> gnpold,
        column.localname -> localname,
        column.governmentform -> governmentform,
        column.headofstate -> headofstate,
        column.capital -> capital,
        column.code2 -> code2
      )
    }.update.apply()

    Country(
      code = code,
      name = name,
      continent = continent,
      region = region,
      surfacearea = surfacearea,
      indepyear = indepyear,
      population = population,
      lifeexpectancy = lifeexpectancy,
      gnp = gnp,
      gnpold = gnpold,
      localname = localname,
      governmentform = governmentform,
      headofstate = headofstate,
      capital = capital,
      code2 = code2)
  }

  def batchInsert(entities: collection.Seq[Country])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        Symbol("code") -> entity.code,
        Symbol("name") -> entity.name,
        Symbol("continent") -> entity.continent,
        Symbol("region") -> entity.region,
        Symbol("surfacearea") -> entity.surfacearea,
        Symbol("indepyear") -> entity.indepyear,
        Symbol("population") -> entity.population,
        Symbol("lifeexpectancy") -> entity.lifeexpectancy,
        Symbol("gnp") -> entity.gnp,
        Symbol("gnpold") -> entity.gnpold,
        Symbol("localname") -> entity.localname,
        Symbol("governmentform") -> entity.governmentform,
        Symbol("headofstate") -> entity.headofstate,
        Symbol("capital") -> entity.capital,
        Symbol("code2") -> entity.code2))
    SQL("""insert into country(
      Code,
      Name,
      Continent,
      Region,
      SurfaceArea,
      IndepYear,
      Population,
      LifeExpectancy,
      GNP,
      GNPOld,
      LocalName,
      GovernmentForm,
      HeadOfState,
      Capital,
      Code2
    ) values (
      {code},
      {name},
      {continent},
      {region},
      {surfacearea},
      {indepyear},
      {population},
      {lifeexpectancy},
      {gnp},
      {gnpold},
      {localname},
      {governmentform},
      {headofstate},
      {capital},
      {code2}
    )""").batchByName(params.toSeq: _*).apply[List]()
  }

  def save(entity: Country)(implicit session: DBSession = autoSession): Country = {
    withSQL {
      update(Country).set(
        column.code -> entity.code,
        column.name -> entity.name,
        column.continent -> entity.continent,
        column.region -> entity.region,
        column.surfacearea -> entity.surfacearea,
        column.indepyear -> entity.indepyear,
        column.population -> entity.population,
        column.lifeexpectancy -> entity.lifeexpectancy,
        column.gnp -> entity.gnp,
        column.gnpold -> entity.gnpold,
        column.localname -> entity.localname,
        column.governmentform -> entity.governmentform,
        column.headofstate -> entity.headofstate,
        column.capital -> entity.capital,
        column.code2 -> entity.code2
      ).where.eq(column.code, entity.code)
    }.update.apply()
    entity
  }

  def destroy(entity: Country)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(Country).where.eq(column.code, entity.code) }.update.apply()
  }

}
