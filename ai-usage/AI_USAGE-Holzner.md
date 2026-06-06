# AI Usage

## Backend Setup und Architekturabgleich
GPT-5
- Ich habe das Backend zuerst anhand des Beispielprojekts und der Unterlagen des Professors selbst aufgesetzt.
- Danach habe ich Codex verwendet, um konkrete Backend-Aufgaben mit derselben Struktur und denselben Technologien zu vervollständigen.
- Dabei habe ich die Quellen des Professors laufend gegengecheckt und Änderungen nicht blind übernommen, sondern Schritt für Schritt mit den Folien abgeglichen.

Beispiel-Prompts:
- `here is how my prof wants it to be. can you check if i missed some best practices`
- `tell me whats wrong and at what page i can see it`

Ergebnis:
- Abgleich mit den Vorlesungsfolien zu `@Entity`, `@Table`, DTOs, Mappern, versionierten API-Pfaden und Package-Struktur
- Anpassung der Backend-Struktur auf `api/v1`, getrennte DTOs, Mapper und Services

Was gut funktioniert hat:
- Architekturfragen mit direktem Bezug auf die Vorlesungsunterlagen
- Schrittweises Prüfen gegen konkrete Seiten der PDFs

Wo mehrfach nachgeschärft werden musste:
- Abgrenzung zwischen Entity, DTO, Mapper und Service
- Frage, welche Änderungen wirklich Best Practice sind und welche nur Stilfragen

## Datenmodell und JPA-Beziehungen
GPT-5
Beispiel-Prompts:
- `look at the Er-Diagram how do i link the roomExtra and roomType to each other?`
- `i could add an extra table for that right? whould that be more best practice?`

Ergebnis:
- Einführung der Many-to-Many-Beziehung zwischen `RoomType` und `RoomExtra`
- Entscheidung, die einfache Variante mit Join-Tabelle für den aktuellen Projektstand zu verwenden

Was gut funktioniert hat:
- Erklärung von relationalen Zusammenhängen und Ableitung der passenden JPA-Annotationen

Wo mehrfach nachgeschärft werden musste:
- Wann `@ManyToMany` ausreicht und wann eine eigene Join-Entity sinnvoller wäre

## Repository, Pagination und API-Form
GPT-5
Beispiel-Prompts:
- `use spring boot data jpa`
- `alright youre right then do it like in this one: https://www.geeksforgeeks.org/advance-java/pagination-and-sorting-with-spring-data-jpa/`

Ergebnis:
- Aufbau des paginierten Endpunkts für `GET /api/v1/room-types`
- Einsatz von `Pageable` und stabiler interner Sortierung nach `id`
- Aktualisierung der API-Dokumentation passend zur tatsächlich implementierten Room-Type-API

Was gut funktioniert hat:
- Technische Umsetzung von Pagination mit Spring Data JPA

Wo mehrfach nachgeschärft werden musste:
- Testbarkeit von `Pageable`

## DTOs, Mapper und Service-Verantwortung
GPT-5
Beispiel-Prompts:
- `alright then add the response entity`
- `the mapping functions in service seem odd. it should be in an extension function right?`

Ergebnis:
- Einführung von Response-DTOs für Room Types und Extras
- Entfernung unnötiger Daten aus der Response, z. B. keine Liste konkreter Rooms im Room-Type-Response
- Auslagerung der Mapping-Logik in eigene Mapper-Klassen

Was gut funktioniert hat:
- Bereinigung der API-Responses auf die tatsächlich benötigten Daten
- Klare Trennung zwischen Persistence-Modell und API-Modell

Wo mehrfach nachgeschärft werden musste:
- Wo Mapping-Logik hingehört
- Wie strikt die Schichten getrennt werden sollen

## Tests und Test-Infrastruktur
GPT-5
Beispiel-Prompts:
- `add tests`
- `how do i add the maven dependencies`
- `Cannot resolve symbol 'DataJpaTest'`
- `any is always not the best solution right?`

Ergebnis:
- Aufbau von Controller-, Service- und Repository-Tests
- Ergänzung der nötigen Test-Dependencies für Spring Boot 4
- Verbesserung der Controller-Tests durch `ArgumentCaptor` statt nur `any(...)`

Was gut funktioniert hat:
- Ableitung passender Testarten pro Schicht
- Verbesserung schwacher Mocking-Ansätze

Wo mehrfach nachgeschärft werden musste:
- Spring-Boot-4-spezifische Test-Dependencies
- Mockito-/Pageable-Mocking

## Seed-Daten
GPT-5
Beispiel-Prompts:
- `yeah i think we should add the seed no?`
- `can you add some bookings to the seed`

Ergebnis:
- Anlegen einer `data.sql` für Room Types, Room Extras, Rooms und Bookings
- Herstellung eines konsistenten lokalen Datenstands für alle Teammitglieder

Was gut funktioniert hat:
- Seed-Daten für statische bzw. Referenzdaten

## User Story 3: Verfügbarkeit prüfen
GPT-5
Beispiel-Prompts:
- `can you add the endpoint thats needed for the userstory 3`
- `add tests. but how do you decide if a room is available or not`
- `raname it prettier`
- `where is the response mapper function btw`

Ergebnis:
- Einführung einer minimalen `Booking`-Entity für U3
- Implementierung des Availability-Endpunkts für Room Types
- Definition der Verfügbarkeitslogik über Anzahl vorhandener Räume minus Anzahl überlappender Buchungen
- Tests für Controller, Service und Repository
- Aktualisierung der API-Dokumentation für den Availability-Endpunkt und die dazugehörigen Fehlerfälle

Was gut funktioniert hat:
- Umsetzung einer klar abgegrenzten fachlichen Logik
- Refactoring langer Spring-Data-Methodennamen in eine lesbarere `@Query`

Wo mehrfach nachgeschärft werden musste:
- Benennung der Repository-Methode
- Frage, ob für den Availability-Response ein eigener Mapper sinnvoll ist

## Peer Review Backend
GPT-5
Beispiel-Prompts:
- `here is the review we got from our backend. discuss together if the review makes sense and if we should implement it into the backend`
- `okay then start with the first step`
- `okay next`

Ergebnis:
- Umstellung des Detail-Endpunkts auf korrektes `404` bei unbekannter ID
- Einführung einer zentralen Fehlerbehandlung mit `@ControllerAdvice`
- Vereinheitlichung des Error-Response-Formats gemäß API-Dokumentation
- Ergänzung von `@Transactional(readOnly = true)` bei lesenden Service-Methoden
- Entfernen redundanter Null-Checks bei `checkIn` und `checkOut`
- Nachziehen der Dokumentation, damit Error-Responses und Backend-Verhalten konsistent bleiben

Was gut funktioniert hat:
- Gemeinsame Einordnung, welche Review-Punkte tatsächlich Backend-relevant sind
- Schrittweises Abarbeiten der sinnvollen Punkte

Wo mehrfach nachgeschärft werden musste:
- Exakte Form des dokumentierten Error-Responses
- Abgrenzung zwischen echten Backend-Aufgaben und reinen Frontend-Hinweisen aus dem Review
- Konsistente Einordnung, welche Änderungen Teil des aktuellen Tickets sind und welche bereits zu weit in Folgefeatures gehen

## Frontend Quellen und Leitlinien
GPT-5
Beispiel-Prompts:
- `have a look into frontend there are all my professors sources. check them carefully. everthing in there has to be exectly like he mentions it.`
- `can you put all the rules into a .agent file that you read automatically each time while working on sth?`

Ergebnis:
- Ich habe die professorenspezifischen Frontend-Regeln aus dem lokalen `frontend/`-Ordner und den externen Referenzen zusammengezogen.
- Daraus entstand eine klare Leitlinie fuer den Booking-Flow, die sich an den Ionic-Vorgaben, den UX-Grundsaetzen und der Vue-Struktur aus den Quellen orientiert.
- Diese Leitlinie diente danach als Arbeitsgrundlage fuer die weiteren Frontend-Schritte.

Was gut funktioniert hat:
- Die Quellen konnten direkt auf konkrete UI- und Architekturfragen gemappt werden.
- Die Leitlinie hat geholfen, spaetere Entscheidungen nicht nur nach Gefuehl, sondern nach den Kursvorgaben zu treffen.

Wo mehrfach nachgeschaerft werden musste:
- Die Grenze zwischen allgemeiner Best Practice und wirklich professorenspezifischen Vorgaben musste mehrfach sauber gezogen werden.

## Frontend Booking Flow
GPT-5
Beispiel-Prompts:
- `refactor the booking page to fullfil these rules`
- `make sure email adresses dont mind if they are upper or lowercase`
- `okay do this`
- `next step`

Ergebnis:
- Der Booking-Flow wurde in kleinere Komponenten aufgeteilt: Route, Summary, Formular, Review und Confirmation.
- Die State-Verwaltung wanderte in Pinia-Stores, damit Buchungszeitraum, Formularzustand und Buchungs-Flow sauber getrennt sind.
- Die Buchungslogik fuehrt von der Raumuebersicht ueber die Detailseite bis zur Booking-Seite und bleibt dabei klar nachvollziehbar.
- Die API wurde mit Request- und Response-Modellen sauber von den UI-Modellen getrennt.
- Die Buchungspost-Logik wurde direkt mit der echten API verbunden; Backend-Fehler werden in nutzerverstaendliche Meldungen uebersetzt.

Was gut funktioniert hat:
- Die Aufteilung in kleine Komponenten hat die Seite deutlich lesbarer gemacht.
- Die Trennung zwischen UI und API hat die Verantwortlichkeiten klarer gemacht.

Wo mehrfach nachgeschaerft werden musste:
- Einige Model-Namen und Zustandszustaende mussten mehrfach angepasst werden, bis die Struktur sauber und konsistent war.
