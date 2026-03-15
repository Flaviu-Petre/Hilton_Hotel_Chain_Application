# Hilton Hotel Chain Application

O aplicație de tip consolă (CLI) dezvoltată în Java pentru gestionarea operațiunilor dintr-un lanț hotelier. Sistemul permite administrarea hotelurilor, a camerelor, a oaspeților și a rezervărilor, având atât o componentă de stocare în memorie, cât și una de persistență a datelor folosind o bază de date PostgreSQL.

## Funcționalități Principale

Prin intermediul meniului interactiv din linia de comandă, utilizatorul poate naviga prin următoarele module:

* **Hotel Management:** Adăugarea de noi hoteluri și vizualizarea detaliilor acestora.
* **Guest Management:** Înregistrarea oaspeților noi, vizualizarea unui oaspete după ID și listarea tuturor oaspeților.
* **Room Management:** Adăugarea camerelor, vizualizarea tipului și disponibilității acestora și filtrarea camerelor pentru un anumit hotel.
* **Reservation Management:** Crearea de rezervări (care modifică automat disponibilitatea camerelor), vizualizarea rezervărilor și anularea acestora.
* **Database Operations:** O secțiune dedicată operațiunilor CRUD (Create, Read, Update, Delete) complete pentru hoteluri, care interacționează direct cu baza de date PostgreSQL.

## Arhitectură și Tehnologii

Proiectul este structurat pe mai multe straturi, respectând principiile de Clean Code și Separation of Concerns:

* **Limbaj:** Java 24
* **Build Tool:** Maven
* **Bază de date:** PostgreSQL (folosind JDBC prin clasele DAO)
* **Testare:** JUnit 5 (Jupiter) și Mockito

**Structura pachetelor:**
* `models`: Clasele de date (Guest, Hotel, Reservation, Room, User).
* `DAOs`: Obiectele de acces la date pentru interacțiunea directă cu baza de date.
* `DataBase`: Conține logica de conectare la PostgreSQL (`DataBaseConnection`) și servicii specifice bazei de date.
* `Repository`: Gestionează stocarea temporară (in-memory) a datelor.
* `Service` & `Interfaces`: Conțin logica de business a aplicației și implementările interfețelor.
* `Enums` & `Exceptions`: Tipuri de date enumerate (ex. Statusul rezervării) și excepții personalizate.

## Rulare și Configurare

1.  **Cerințe preliminare:**
    * Java Development Kit (JDK) 24 instalat.
    * Apache Maven instalat.
    * Un server PostgreSQL rulând pe mașina locală.

2.  **Configurarea Bazei de Date:**
    * Creați o bază de date numită `HotelDB` în PostgreSQL.
    * Navigați la fișierul `src/main/java/DataBase/DataBaseConnection.java`.
    * Actualizați variabilele `_username` și `_password` cu credențialele serverului dumneavoastră de PostgreSQL:
        ```java
        String _url = "jdbc:postgresql://localhost:5432/HotelDB";
        String _username = "USERNAME-UL_TAU";
        String _password = "PAROLA_TA";
        ```

3.  **Rularea aplicației:**
    Aplicația poate fi rulată din IDE-ul dumneavoastră preferat (ex: IntelliJ IDEA, Eclipse) prin executarea clasei `org.example.Main`.

4.  **Rularea testelor:**
    Pentru a rula testele unitare (ex. `ReservationRepositoryTest`), puteți rula comanda Maven:
    ```bash
    mvn test
    ```
