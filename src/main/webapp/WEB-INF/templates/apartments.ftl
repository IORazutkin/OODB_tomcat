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
        <li><a href='${model["app_path"]}'>Главная</a></li>
        <li><a href='${model["app_path"]}/services'>Услуги</a></li>
        <li><a href='${model["app_path"]}/houses'>Дома</a></li>
        <li><a class="active" href='${model["app_path"]}/apartments'>Квартиры</a></li>
        <li><a href='${model["app_path"]}/receipts'>Квитанции</a></li>
    </ul>
</div>
<div class="c-wrapper">
    <form action="${model["app_path"]}/apartment-add" method="post" class="line">
        <div class="line__item">
            <select name="house">
                <#list model["houses"] as house>
                    <option value="${house.id}">${house.address}</option>
                </#list>
            </select>
        </div>
        <div class="line__item">
            <input name="number" type="number">
        </div>
        <div class="line__item">
            <button name="action" type="submit" value="save" class="btn btn-save">Добавить</button>
        </div>
        <div class="line__item">
            <button name="action" type="submit" value="search" class="btn btn-search">Поиск по адресу</button>
        </div>
    </form>
    <#if model["apartments"]??>
        <#list model["apartments"] as apartment>
            <form action="${model["app_path"]}/apartment-action" method="post" class="line">
                <div class="line__item" style="position: absolute">
                    <#if apartment.id??>
                        <input name="id" type="hidden" value="${apartment.id}">
                    </#if>
                </div>
                <div class="line__item">
                    <#if apartment.house??>
                        <select name="house">
                            <#list model["houses"] as house>
                                <#if apartment.house.id == house.id>
                                    <option value="${house.id}" selected>${house.address}</option>
                                <#else>
                                    <option value="${house.id}">${house.address}</option>
                                </#if>
                            </#list>
                        </select>
                    </#if>
                </div>
                <div class="line__item">
                    <#if apartment.number??>
                        <input name="number" type="number" value="${apartment.number}">
                    </#if>
                </div>
                <div class="line__item">
                    <button name="action" type="submit" value="save" class="btn btn-save">Сохранить</button>
                </div>
                <div class="line__item">
                    <button name="action" type="submit" value="delete" class="btn btn-delete">Удалить</button>
                </div>
            </form>
        </#list>
    </#if>
</div>
</body>
</html>