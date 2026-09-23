<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Tür İşlemleri</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<style>
    body { background-color: #f8f9fa; padding-top: 20px; padding-bottom: 40px; }
    .form-container { background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); margin-bottom: 20px; }
    .table-container { background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    h2 { color: #333; font-weight: bold; margin-bottom: 30px; }
</style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Tür Yönetim Paneli</h2>

        <div class="row">
            <!-- Sol Taraf: Ekleme ve Güncelleme Formları -->
            <div class="col-md-5">
                <div class="form-container">
                    <h4 class="text-primary" style="border-bottom: 2px solid #337ab7; padding-bottom: 8px; margin-bottom: 15px;">Tür Ekle</h4>
                    <form method="post" name="Tur" action="turKaydet">
                        <div class="form-group">
                            <label>Cins Adı:</label>
                            <select name="cinsid" class="form-control">
                                <c:forEach items="${cinsList}" var="item">
                                    <option value="<c:out value='${item.id}' />"><c:out value="${item.ad}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Tür Adı:</label>
                            <input name="ad" class="form-control" required />
                        </div>
                        <div class="form-group">
                            <label>Açıklama:</label>
                            <textarea name="aciklama" rows="3" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Tür Aktiflik:</label><br>
                            <input name="aktifveyadegil" checked data-toggle="toggle" data-onstyle="success" type="checkbox" data-on="Aktif" data-off="Pasif">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Kaydet</button>
                    </form>
                </div>

                <div class="form-container">
                    <h4 class="text-warning" style="border-bottom: 2px solid #f0ad4e; padding-bottom: 8px; margin-bottom: 15px;">Tür Güncelle</h4>
                    <form method="post" name="TurG" action="turGuncelle">
                        <div class="form-group">
                            <label>Değiştirilecek Tür:</label>
                            <select name="id" class="form-control">
                                <c:forEach items="${turList}" var="item">
                                    <option value="<c:out value='${item.id}' />"><c:out value="${item.ad}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Yeni Tür Adı:</label>
                            <input name="ad" class="form-control" required />
                        </div>
                        <div class="form-group">
                            <label>Yeni Cins Adı:</label>
                            <select name="cinsid" class="form-control">
                                <c:forEach items="${cinsList}" var="i">
                                    <option value="<c:out value='${i.id}' />"><c:out value="${i.ad}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Açıklama:</label>
                            <textarea name="aciklama" rows="3" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Tür Aktiflik:</label><br>
                            <input name="aktifveyadegil" checked data-toggle="toggle" data-onstyle="success" type="checkbox" data-on="Aktif" data-off="Pasif">
                        </div>
                        <button type="submit" class="btn btn-warning btn-block" style="color: #fff;">Güncelle</button>
                    </form>
                </div>
            </div>

            <!-- Sağ Taraf: Filtreleme, Rapor ve Tablo -->
            <div class="col-md-7">
                <div class="table-container">
                    <div class="row" style="margin-bottom: 15px;">
                        <div class="col-md-12 text-right">
                            <a href="turRapor" target="_blank" class="btn btn-warning" style="margin-right: 5px;">
                                <span class="glyphicon glyphicon-print"></span> Rapor Al
                            </a>
                            <form name="idogr" action="turFiltrele" method="get" class="form-inline" style="display: inline-block;">
                                <div class="form-group">
                                    <select name="cinsid" class="form-control">
                                        <option value="">Tümü</option>
                                        <c:forEach items="${cinsList}" var="i">
                                            <option value="<c:out value='${i.id}' />"><c:out value="${i.ad}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-info">Filtrele</button>
                            </form>
                        </div>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr style="background-color: #f1f1f1;">
                                    <th>Cins Adı</th>
                                    <th>Tür Adı</th>
                                    <th>Aktiflik</th>
                                    <th>Açıklama</th>
                                    <th class="text-center" style="width: 80px;">İşlem</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${turList}" var="item">
                                    <tr>
                                        <td><c:out value="${item.cinsadi}" /></td>
                                        <td><c:out value="${item.ad}" /></td>
                                        <td><c:out value="${item.aktifveyadegil}" /></td>
                                        <td><c:out value="${item.aciklama}" /></td>
                                        <td class="text-center">
                                            <a href="silTur/${item.id}" class="btn btn-danger btn-xs" title="Sil">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div style="margin-top: 20px;">
                        <a href="cins" class="btn btn-default">
                            <span class="glyphicon glyphicon-arrow-left"></span> Cins İşlemleri
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>