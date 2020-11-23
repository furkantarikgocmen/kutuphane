create schema IF NOT EXISTS kutuphane_schema;

INSERT INTO kutuphane_schema."role" (id,name) VALUES
	 (1,'ADMIN'),
	 (2,'MODERATOR'),
	 (3,'USER');

INSERT INTO kutuphane_schema.account (account_id,mail,"name","password",surname,user_name,role_id) VALUES
	 ('47852672-38f0-4cf6-adde-fdf0cfae5178','admin@kutuphane.com','Furkan Tarık','$2a$10$q2awP67azUvY0a9aJgMJaeQhmN4JrO.4jJrihHulwOWyL3p4H4Dlm','Göçmen','admin',1),
	 ('169a0f97-9fd0-49ea-93c6-1efd5c3a1b47','moderator@kutuphane.com','Furkan Tarık','$2a$10$q2awP67azUvY0a9aJgMJaeQhmN4JrO.4jJrihHulwOWyL3p4H4Dlm','Göçmen','moderator',2),
	 ('0ea412ac-0c56-4afd-84ff-1d2c63188cad','user@kutuphane.com','Furkan Tarık','$2a$10$U7FcaxBPJikGHiS6GBl7xONjEHh0DRSndr/Vt6oJjIw9ZuFx0l3ZG','Göçmen','user',3);


INSERT INTO kutuphane_schema.author (id,description,"name",surname) VALUES
	 ('d754efb6-259d-4a52-bf3f-146ee750c9d0','Rus tiyatro yazınının ve modern kısa öykünün büyük ustalarından biri sayılan ünlü yazar, 1880''li yıllarda Moskova Üniversitesi''nde tıp eğitimi görürken, dönemin mizah dergilerinde ve gazetelerinde öykü ve kısa anlatılarını yayımlamaya başladı.','Anton','Çehov'),
	 ('fbf071ea-f3ec-48ad-a060-308b641ace84','Stefan Zweig 1881 yılında Viyana''da doğdu. Babası varlıklı bir sanayiciydi. Viyana ve Berlin''de eğitim gördü. Birçok ülkeyi dolaştıktan sonra Birinci Dünya Savaşı sırasında, Zürih''e geldi. Savaş karşıtı kişiliğiyle tanındı','Stefan','Zweig'),
	 ('30e466b3-0798-4482-8db2-014299716e5b','25 Şubat 1907’de Gümülcine’de doğdu, 2 Nisan 1948’de Kırklareli’nde öldü.1948’de bir yazısı yüzünden tutuklandı, üç ay kadar hapis yattı.','Sabahattin','Ali'),
	 ('b346bfd2-065a-4c82-9ab1-38555b4086d9','Orhan Pamuk 1952''de İstanbul''da doğdu. Cevdet Bey ve Oğulları ve Kara Kitap adlı romanlarında anlattığına benzer kalabalık bir ailede ve şehrin Batılılaşmış ve zengin semti Nişantaşı''nda büyüyüp yetişti.','Orhan','Pamuk'),
	 ('2689eea0-b3ac-4894-a8f7-2301afb6f16b','Michael Ende, 1929 yılında Almanya’da dünyaya geldi. II. Dünya Savaşı’ndan sonra bir drama okuluna katıldı, aktörlük yaptı, skeçler ve kısa oyunlar yazdı, tiyatro yönetmenliği ve film eleştirmenliği yaptı.','Michael','Ende'),
	 ('833e2cfe-0c0b-476b-9048-94464f1c7ba3',' ','Tina Payne',' Bryson'),
	 ('df5b2d92-58bd-44b0-852a-2f9bee3d9b83','Romanları 30 dilde yayımlanan Zülfü Livaneli, 1946 yılında doğdu. 1972 yılında fikirlerinden dolayı askeri cezaevinde yattı, 11 yıl sürgünde yaşadı. Livaneli, 1999 yılında San Remo’da En İyi Besteci ödülüne layık görüldü.','Zülfü','Livaneli'),
	 ('094ddce6-74a6-4f5a-a078-3b1d2488d8a1','Fransız yazar Jean-Christophe Grangé 1961’de Paris’te doğdu. Serbest gazeteci olarak çeşitli haber ajansları ve gazeteler için çalıştı.','Jean Christophe','Grange');


INSERT INTO kutuphane_schema.book (id,description,ısbn,"name",series_name,sub_name) VALUES
	 ('6b346a02-45dd-4601-a6b0-e5514f864fa7','Çehov bir taşra kasabasındaki akıl hastanesinde geçen bu novellasında, eğitimli bir hasta olan İvan Dmitriç ile Doktor Andrey Yefimıç arasındaki felsefi çatışmaya odaklanır.','	9786052951569','Altıncı Koğuş','Hikaye',' '),
	 ('58bbc888-b2ea-4382-ab07-6b273fb3e74c','Bilinmeyen Bir Kadının Mektubu''nun kadın kahramanını sadece uzun bir mektubun yazarı olarak tanıyoruz. Kadının hayatı boyunca sevmiş olduğu erkek için kaleme aldığı bu mektubun "gönderen"inin adı yoktur.','9786053606604','Bilinmeyen Bir Kadının Mektubu','Hikaye(Çeviri)',''),
	 ('54cc10b8-0e58-4d11-b79f-ff81d5c8d4ea','Olağanüstü Bir Gece, seçkin bir burjuva olarak rahat ve tasasız varoluşunu sürdürürken giderek duyarsızlaşan bir adamın hayatındaki dönüştürücü deneyimin hikâyesidir.','9786053326090','Olağanüstü Bir Gece','Roman(Çeviri)',''),
	 ('472f48d8-b7ad-4a0b-a8f6-7dcf94548830','"İsteyip istemediğimi doğru dürüst bilmediğim, fakat neticesi aleyhime çıkarsa istemediğimi iddia ettiğim bu nevi söz ve fiillerimin daimi bir mesulünü bulmuştum.','9789753638036','İçimizdeki Şeytan','',''),
	 ('0c45605b-7935-4713-93e4-097f06fa955c','steyip istemediğimi doğru dürüst bilmediğim, fakat neticesi aleyhime çıkarsa istemediğimi iddia ettiğim bu nevi söz ve fiillerimin daimi bir mesulünü bulmuştum:','9789753638029','Kürk Mantolu Madonna','Roman(Yerli)',''),
	 ('e971445f-18ad-405d-941c-e0e56ade70e9','Hayatımın en mutlu ânıymış, bilmiyordum.”
Nobel ödüllü büyük yazarımız Orhan Pamuk''un harikulade aşk romanı bu sözlerle başlıyor...','9789750826146','Masumiyet Müzesi','Roman(Yerli)',''),
	 ('5a07d787-ec94-4e86-9a68-b3c4db27080b','İnsanlar vardır, asla Fantazya’ya gidemezler,” dedi Bay Koreander. “Ve insanlar vardır, gidebilirler ama sonsuza kadar orada kalırlar.','9786052993125','Bitmeyecek Öykü','Bilim-Kurgu','Die unendliche Geschichte'),
	 ('7ffcee40-0a54-4514-a383-e45f3ea30407','Momo, büyük bir kentin tiyatro harabelerinde yaşayan küçük bir kızdır. Buldukları ya da kendisine hediye edilenler dışında hiçbir şeyi yoktur','9786052993019','MOMO','Roman(Çeviri)',''),
	 ('fc121cc7-d1c6-417b-a416-983b28b7bc53','Çoksatan kitaplar yazan alanlarında öncü uzmanlar, şimdi de çocuk yetiştirmedeki en büyük zorluğu araştırıyorlar: DİSİPLİN. ','9786052994108','Dramsız Disiplin','Aile-Psikoloji','No-Drama Discipline'),
	 ('574eb87f-5bed-413f-9949-32008bf43014','Sakin bir balıkçı köyünde genç bir kadının cinayete kurban gitmesiyle başlar her şey. Dünyadan elini eteğini çekmiş emekli inşaat mühendisiyle genç, güzel ve meraklı gazeteci kızın tanışmasına da bu cinayet vesile olur.','9786050914443','Kardeşimin Hikayesi','Roman(Yerli)','');
INSERT INTO kutuphane_schema.book (id,description,ısbn,"name",series_name,sub_name) VALUES
	 ('cf3081a0-7761-4c1e-bfe0-fe5bf3e80ded','Her şey, 2001 yılının Şubat ayında soğuk bir gün, İstanbul Üniversitesi’nde halkla ilişkiler görevini yürüten Maya Duran’ın (36) ABD’den gelen Alman asıllı Profesör Maximilian Wagner’i (87) karşılamasıyla başlar.','9786050900286','Serenad','Roman(Yerli)',''),
	 ('6520d0f9-3d83-46aa-8099-9d13c3109e55','Göçmen kuşlardır Leylekler. Her bahar Avrupa''ya gelir, yaz sonunda tekrar Afrikaya doğru yola çıkarlar. Ama bu yıl geri dönmeyecekler...','9789759914349','Leyleklerin Uçuşu','Polisiye','Le vol des cigognes');


INSERT INTO kutuphane_schema.publisher (id,info,"name") VALUES
	 ('f2566b17-eac3-4b10-9945-b2f53066fd6c','www.isbank.com 05343343434','TÜRKİYE İŞ BANKASI KÜLTÜR YAYINLARI'),
	 ('9349ba88-1a5c-49e6-9ce4-67962b5adba6','www.yapikredi.com 0312331212','YAPI KREDİ YAYINLARI'),
	 ('7519daa4-3efc-447c-a738-03ba43c32f85','www.pegasusyayinevi.com ','PEGASUS YAYINLARI'),
	 ('25606010-b1de-4209-9772-6a8a8b0240fa','www.doganyayin.com','DOĞAN KİTAP');

INSERT INTO kutuphane_schema.book_author (author_id,book_id) VALUES
	 ('d754efb6-259d-4a52-bf3f-146ee750c9d0','6b346a02-45dd-4601-a6b0-e5514f864fa7'),
	 ('fbf071ea-f3ec-48ad-a060-308b641ace84','58bbc888-b2ea-4382-ab07-6b273fb3e74c'),
	 ('fbf071ea-f3ec-48ad-a060-308b641ace84','54cc10b8-0e58-4d11-b79f-ff81d5c8d4ea'),
	 ('30e466b3-0798-4482-8db2-014299716e5b','472f48d8-b7ad-4a0b-a8f6-7dcf94548830'),
	 ('30e466b3-0798-4482-8db2-014299716e5b','0c45605b-7935-4713-93e4-097f06fa955c'),
	 ('b346bfd2-065a-4c82-9ab1-38555b4086d9','e971445f-18ad-405d-941c-e0e56ade70e9'),
	 ('2689eea0-b3ac-4894-a8f7-2301afb6f16b','5a07d787-ec94-4e86-9a68-b3c4db27080b'),
	 ('2689eea0-b3ac-4894-a8f7-2301afb6f16b','7ffcee40-0a54-4514-a383-e45f3ea30407'),
	 ('833e2cfe-0c0b-476b-9048-94464f1c7ba3','fc121cc7-d1c6-417b-a416-983b28b7bc53'),
	 ('df5b2d92-58bd-44b0-852a-2f9bee3d9b83','574eb87f-5bed-413f-9949-32008bf43014');
INSERT INTO kutuphane_schema.book_author (author_id,book_id) VALUES
	 ('df5b2d92-58bd-44b0-852a-2f9bee3d9b83','cf3081a0-7761-4c1e-bfe0-fe5bf3e80ded'),
	 ('094ddce6-74a6-4f5a-a078-3b1d2488d8a1','6520d0f9-3d83-46aa-8099-9d13c3109e55');

INSERT INTO kutuphane_schema.book_publisher (publisher_id,book_id) VALUES
	 ('f2566b17-eac3-4b10-9945-b2f53066fd6c','6b346a02-45dd-4601-a6b0-e5514f864fa7'),
	 ('f2566b17-eac3-4b10-9945-b2f53066fd6c','58bbc888-b2ea-4382-ab07-6b273fb3e74c'),
	 ('f2566b17-eac3-4b10-9945-b2f53066fd6c','54cc10b8-0e58-4d11-b79f-ff81d5c8d4ea'),
	 ('9349ba88-1a5c-49e6-9ce4-67962b5adba6','472f48d8-b7ad-4a0b-a8f6-7dcf94548830'),
	 ('9349ba88-1a5c-49e6-9ce4-67962b5adba6','0c45605b-7935-4713-93e4-097f06fa955c'),
	 ('9349ba88-1a5c-49e6-9ce4-67962b5adba6','e971445f-18ad-405d-941c-e0e56ade70e9'),
	 ('7519daa4-3efc-447c-a738-03ba43c32f85','5a07d787-ec94-4e86-9a68-b3c4db27080b'),
	 ('7519daa4-3efc-447c-a738-03ba43c32f85','7ffcee40-0a54-4514-a383-e45f3ea30407'),
	 ('7519daa4-3efc-447c-a738-03ba43c32f85','fc121cc7-d1c6-417b-a416-983b28b7bc53'),
	 ('25606010-b1de-4209-9772-6a8a8b0240fa','574eb87f-5bed-413f-9949-32008bf43014');
INSERT INTO kutuphane_schema.book_publisher (publisher_id,book_id) VALUES
	 ('25606010-b1de-4209-9772-6a8a8b0240fa','cf3081a0-7761-4c1e-bfe0-fe5bf3e80ded'),
	 ('25606010-b1de-4209-9772-6a8a8b0240fa','6520d0f9-3d83-46aa-8099-9d13c3109e55');

