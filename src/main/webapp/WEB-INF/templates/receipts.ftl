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
        <li><a href='${model["app_path"]}/apartments'>Квартиры</a></li>
        <li><a class="active" href='${model["app_path"]}/receipts'>Квитанции</a></li>
    </ul>
</div>
<div class="c-wrapper">
    <form action="${model["app_path"]}/receipt-add" method="post" class="line">
        <div class="line__item">
            <select name="apartment">
                <#list model["apartments"] as a>
                    <option value="${a.id}">${a.house.address + ', ' + a.number}</option>
                </#list>
            </select>
        </div>
        <div class="line__item">
            <select name="service">
                <#list model["services"] as s>
                    <option value="${s.id}">${s.title}</option>
                </#list>
            </select>
        </div>
        <div class="line__item">
            <input name="value" type="number">
        </div>
        <div class="line__item">
            <button name="action" type="submit" value="save" class="btn btn-save">Добавить</button>
        </div>
    </form>
    <#if model["receipts"]??>
        <#list model["receipts"] as r>
            <form action="${model["app_path"]}/receipt-action" method="post" class="line">
                <div class="line__item" style="position: absolute">
                    <#if r.id??>
                        <input name="id" type="hidden" value="${r.id}">
                    </#if>
                </div>
                <div class="line__item">
                    <#if r.apartment??>
                        <select name="apartment">
                            <#list model["apartments"] as a>
                                <#if r.apartment.id == a.id>
                                    <option value="${a.id}" selected>${a.house.address + ', ' + a.number}</option>
                                <#else>
                                    <option value="${a.id}">${a.house.address + ', ' + a.number}</option>
                                </#if>
                            </#list>
                        </select>
                    </#if>
                </div>
                <div class="line__item">
                    <#if r.service??>
                        <select name="service">
                            <#list model["services"] as s>
                                <#if r.service.id == s.id>
                                    <option value="${s.id}" selected>${s.title}</option>
                                <#else>
                                    <option value="${s.id}">${s.title}</option>
                                </#if>
                            </#list>
                        </select>
                    </#if>
                </div>
                <div class="line__item">
                    <#if r.value??>
                        <input name="value" type="number" value="${r.value}">
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