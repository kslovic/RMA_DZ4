# RMA_DZ4
Pri pokretanju aplikacije prikazuje se početni zaslon s listom vijesti koje je moguće otvoriti klikom na 
pojedinu vijest. Listu vijesti je moguće osvježiti pull to refresh metodom. Vijesti je moguće i pretraživati 
po kategorijama, a rezultati se prikazuju odmah pri upisu. Prikaz liste ostvaren je korištenjem ListView elementa. 
Lista podatke vuče iz niza objekata News koji se povlače iz baze podataka u koju su prije spremljeni podatci koji 
su povučeni sa stranice bug.hr u xml formatu. Učitavanje podataka iz xml u bazu podataka izvodi se unutar klase 
getNewsInfo() u kojoj se nahprije dohvaća xml element te nakon toga raščlanjuje na elemente čiji sadržaj ili 
atribute spremamo unutar stringova koji se predaju konstruktoru klase News. Nakon toga poziva se funkcija 
insertNews koja objekt klase News sprema u bazu podataka. Nakon spremanja u bazu dohvaćaju se svi objekti News iz 
baze podataka s getAllNews() funkcijom i spremaju u niza objekata klase News. Taj niz se zatim prosljeđuje kao parametar
funkciji displayNews(). U toj funkciji kreira se novi adapter kojem se prosljeđuje niz objekata klase News čiji će podatci
biti prikazani u listi. Na item-e liste postavlja se listener metoda koja osluškuje klik i ukoliko do njega dođe kreira
se novi intent kojem se prosljeđuje link vijesti tako da se ona otvori na klik. Prikaz rezultata odmah pri upisu 
kategorije ostvaren je s TextWatcher-om koji osluškuje svaku promjenu teksta i nakon promjene pretražuju vijesti 
iz baze podataka koje su dohvaćene funkcijom getNews kojoj se kao parametar predaje unos koji se pretražuje. Nakon što
se dohvate vijesti s željenom kategorijom prikazuju se u ListView elementu. Pull to refresh je ostvaren pomoću 
OnRefreshListener-a u kojem se definira onRefresh funkcija u kojoj se vijesti ponovno učitavaju s weba i prikazuju
odnosno izvršava se ponovno objekt klase GetNewsInfo(). Da bi pull to refresh bio ostvaren također je potrebno ugnjezditi 
ListView unutar SwipeRefreshLayout-a. 

Izvori:
[1]Predlošci s loomena
[2]https://www.youtube.com/channel/UCNgv2PhyJmssBUigpmVMldA/videos  
#1-#4 How to fetch rss feeds part 1 : android xml parser tutorials, #5-#8 Android recyclerview and cardview part 1 : android xml parser tutorials
[3]http://stackoverflow.com/questions/11863038/how-to-get-the-attribute-value-of-an-xml-node-using-java
[4]http://stacktips.com/tutorials/android/android-textwatcher-example
[5]http://guides.codepath.com/android/implementing-pull-to-refresh-guide
