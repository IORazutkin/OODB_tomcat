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
        <li><a class="active" href='${model["app_path"]}/services'>Услуги</a></li>
        <li><a href='${model["app_path"]}/houses'>Дома</a></li>
        <li><a href='${model["app_path"]}/apartments'>Квартиры</a></li>
        <li><a href='${model["app_path"]}/receipts'>Квитанции</a></li>
    </ul>
</div>
<div class="c-wrapper">
    <form action="${model["app_path"]}/service-add" method="post" class="line">
        <div class="line__item">
            <input name="title" placeholder="Название">
        </div>
        <div class="line__item">
            <input name="price" type="number" placeholder="Цена">
        </div>
        <div class="line__item">
            <input name="unit" placeholder="Единица измерения">
        </div>
        <div class="line__item">
            <button name="action" type="submit" value="save" class="btn btn-save">Добавить</button>
        </div>
    </form>
    <#if model["services"]??>
        <#list model["services"] as service>
            <form action="${model["app_path"]}/service-action" method="post" class="line">
                <div class="line__item" style="position: absolute">
                    <#if service.id??>
                        <input name="id" type="hidden" value="${service.id}">
                    </#if>
                </div>
                <div class="line__item">
                    <#if service.title??>
                        <input name="title" value="${service.title}">
                    </#if>
                </div>
                <div class="line__item">
                    <#if service.price??>
                        <input name="price" type="number" value="${service.price}">
                    </#if>
                </div>
                <div class="line__item">
                    <#if service.unit??>
                        <input name="unit" value="${service.unit}">
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