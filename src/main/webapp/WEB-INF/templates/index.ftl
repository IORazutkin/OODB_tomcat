<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<link rel='stylesheet' href='${model["app_path"]}/css/lab10.css'>
	<link rel='stylesheet' href='${model["app_path"]}/css/form.css'>

	<title>Коммунальные услуги</title>
</head>
<body>
<div class="menu-bar">
	<ul>
		<li><a class="active" href='${model["app_path"]}'>Главная</a></li>
		<li><a href='${model["app_path"]}/services'>Услуги</a></li>
		<li><a href='${model["app_path"]}/houses'>Дома</a></li>
		<li><a href='${model["app_path"]}/apartments'>Квартиры</a></li>
		<li><a href='${model["app_path"]}/receipts'>Квитанции</a></li>
	</ul>
</div>
<div class="c-wrapper">
	<h3>Предмтная область: <b>Коммунальные услуги</b></h3>
	<h3>Сущности:</h3>
	<dl>
		<dt>Apartment</dt>
		<dd>
			<pre>
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;

    @ManyToOne(targetEntity = House.class)
    @JoinColumn(name = "house_id")
    House house;
    int number;

    public Apartment() {}

    public Apartment(House house, int number) {
        this.house = house;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
			</pre>
		</dd>
		<dt>House</dt>
		<dd>
			<pre>
@Entity
public class House {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;
    String address;

    public House() {}

    public House(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }
}
			</pre>
		</dd>
		<dt>Receipt</dt>
		<dd>
			<pre>
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;
    @ManyToOne(targetEntity = Service.class)
    @JoinColumn(name = "service_id")
    Service service;
    double value;
    @ManyToOne(targetEntity = Apartment.class)
    @JoinColumn(name = "apartment_id")
    Apartment apartment;
    LocalDate formationDate;

    public Receipt() {
    }

    public Receipt(Service service, double value, Apartment apartment) {
        this.service = service;
        this.value = value;
        this.apartment = apartment;
        this.formationDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public LocalDate getFormationDate() {
        return formationDate;
    }

    public void setFormationDate(LocalDate formationDate) {
        this.formationDate = formationDate;
    }
}
			</pre>
		</dd>
		<dt>Service</dt>
		<dd>
			<pre>
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;
    String title;
    double price;
    String unit;

    public Service() {
    }

    public Service(String title, double price, String unit) {
        this.title = title;
        this.price = price;
        this.unit = unit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
			</pre>
		</dd>
		<dt>SQL</dt>
		<dd>
			<pre>
create table Apartment (
	id int8 not null,
	number int4 not null,
	house_id int8,
	primary key (id)
);
create table House (
	id int8 not null,
	address varchar(255),
	primary key (id)
);
create table Receipt (
	id int8 not null,
	formationDate date,
	value float8 not null,
	apartment_id int8,
	service_id int8,
	primary key (id)
);
create table Service (
	id int8 not null,
	price float8 not null,
	title varchar(255),
	unit varchar(255),
	primary key (id)
);
			</pre>
		</dd>
	</dl>
</div>
</body>
</html>