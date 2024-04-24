# Aplikacja "Programex Weather App"
Aplikacja "Programex Weather App" to narzędzie pomagające użytkownikowi w wyborze odpowiedniego ubioru na podstawie prognozy pogody. Program analizuje dane pogodowe pobrane za pomocą zewnętrznego API aby zwrócić krótkie informacje, które pozwolą na szybkie podjęcie decyzji o ubiorze na dany dzień. 

Projekt został zaimplementowany w języku Java, wykorzystując wzorzec projektowy MVP (Model-View-Presenter).

---

## Spis treści
1. [Technologie](#technologie)
2. [Zewnętrzne API](#zewnętrzne-api)
2. [Uruchomienie projektu](#uruchomienie-projektu)
3. [Funkcjonalności](#funkcjonalności)
4. [Milestone 3](#milestone-3)

---

## Technologie
- Java (wersja > 8 )
- Gradle (wersja >= 7 )

---

## Zewnętrzne API
W projekcie zostały użyte następujące API:
- WeatherAPI (https://www.weatherapi.com/)
- IPinfoAPI (https://ipinfo.io/)

---

## Uruchomienie projektu
a. Do umożliwienia poprawnego działania WeatherAPI należy do głównego folderu projektu dodać plik `.env`. Wymagana zawartość pliku:
```
API_KEY = YOUR_API_KEY
```
Klucz API należy wygenerować z oficjalnej strony WeatherAPI (wymagane jest utworzenie darmowego konta)
Do poprawnego działania klucza należy upewnić się, że pola przy 'forecastDay' w panelu "API Response Fields" są zaznaczone.


b. Projekt uruchamiany jest za pomocą komendy `gradlew run`.

---

## Funkcjonalności

Program umożliwia zaprezentowanie pogody w dwóch wariantach: 
aktualna pogoda w jednej lokalizacji (np. w okolicach domu)
aktualna analiza pogodowa na podstawie danych z dwóch lokalizacji (np. dom i praca)

W przypadku pierwszej lokalizacji możliwe jest użycie automatycznej geolokacji za pomocą dodatkowego przycisku.

Informacje otrzymane przez użytkownika:
- temperatura odczuwalna (w 4 stopniowej skali: bardzo zimno, zimno, ciepło, bardzo ciepło)
- zachmurzenie (4 stopniowa skala)
- informacja czy należy zabrać parasol
- informacja czy należy zabrać maskę antysmogową

---

## Analiza wyników
Poniżej opisany jest sposób analizy poszczególnych wartości pogodowych ukazanych użytkownikowi
### Temperatura odczuwalna
Temperatura odczuwalna obliczana jest za pomocą wzoru Model 1 - temperatura-wiatr:
https://pl.wikipedia.org/wiki/Temperatura_odczuwalna

Na podstawie średniej temperatury z obu lokacji oraz maksymalnej prędkości wiatru wyliczana jest wartość temperatury odczuwalnej.
Wartość ta jest następnie przypisana za pomocą 4 stopniowej skali.
### Zachmurzenie
Używana wartość zachmurzenia to maksymalne zachmurzenie z obu lokacji.
### Parasol
Jeżeli w którymkolwiek miejscu opady przekraczają ustalony treshhold, użytkownik otrzyma sugestię o zabraniu parasola
### Maska antysmogowa
Jeżeli w którymkolwiek miejscu jakość powietrza przekracza ustalone normy, użytkownik otrzyma sugestię zaopatrzenia się w maskę antysmogową

## Milestone 3
Do naszej aplikacji zostały dodane następujące funkcjonalności:

- zapisywanie ulubionych lokalizacji w bazie z możliwością szybkiej aktualizacji
- sprawdzenie danych pogodowych dla większej liczby lokalizacji
- możliwość wybrania godziny, dla jakiej ma zostać sprawdzona pogoda (maksymalnie 24h w przód)
- nowa, dokładniejsza skala temperatury odczuwalnej (5 stopni)