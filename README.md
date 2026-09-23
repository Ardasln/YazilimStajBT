# StajBT - Ağaç Cins ve Tür Yönetim Sistemi

Bu proje, yazılım stajı kapsamında geliştirilmiş, ağaç cinsleri ve türlerinin (Cins ve Tür) detaylı olarak yönetilmesini sağlayan web tabanlı bir otomasyon sistemidir.

## 🚀 Özellikler

* **Kapsamlı CRUD İşlemleri:** Cins ve Tür modülleri için ekleme, silme, güncelleme ve filtreli listeleme.
* **Dinamik PDF Raporlama:** JasperReports altyapısı kullanılarak veritabanındaki kayıtların anlık olarak PDF formatında dışa aktarılması.
* **Modern Arayüz:** Bootstrap kullanılarak tasarlanmış, kullanıcı dostu ve duyarlı (responsive) web tasarımı.
* **İlişkisel Veri Yönetimi:** Tür kayıtlarının Cins verileriyle (cinsid üzerinden) dinamik olarak bağlanması ve yönetilmesi.

## 🛠️ Kullanılan Teknolojiler

* **Backend:** Java, Spring MVC
* **Veritabanı:** MongoDB (MongoTemplate)
* **Frontend:** JSP, JSTL, HTML, CSS, Bootstrap, jQuery
* **Raporlama Aracı:** JasperReports (.jrxml / .jasper)
* **Sunucu:** Apache Tomcat 9

## ⚙️ Kurulum ve Çalıştırma

1. Projeyi bilgisayarınıza klonlayın: `git clone https://github.com/Ardasln/YazilimStajBT.git`
2. Bilgisayarınızda **MongoDB** sunucusunun (localhost:27017) çalıştığından emin olun.
3. Projeyi Eclipse veya tercih ettiğiniz bir IDE'ye (Maven projesi veya Dynamic Web Project olarak) import edin.
4. Gerekli kütüphane bağımlılıklarının yüklendiğinden emin olun.
5. Projeyi Apache Tomcat sunucusu üzerinde başlatın.

## 👨‍💻 Geliştirici

**Arda Aslan** - Bilgisayar Mühendisliği Öğrencisi
