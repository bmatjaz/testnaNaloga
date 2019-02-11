# testnaNaloga

Delujoča testna naloga z implementiranimi željenimi metodami za delo in pregled podatkov v bazi.

Za podatkovno bazo je uporabljen inmemory podatkovna baza H2.
Za izdelavo tabele in delo s podatki sem uporabil Hibernate.

Podprte funkcije so:
- POST create prejme: medName (string), dosage (int), unit (string), start_time (timestamp), end_time (timestamp), timing_hours_period (int)
- PUT update prejme: medName (string), dosage (int), unit (string), start_time (timestamp), end_time (timestamp), timing_hours_period (int), id (int)
- DELETE delete prejme: id (int)
- GET getOne prejme id (int) 
- GET getAll ne prejme parametrov
- GET getBetween prejme: startTime (timestamp), endTime (timeStamp)

*timestamp je v obliki yyyy-mm-dd hh:mm:ss.000


Program se lahko zažene kot samostojna aplikacija z buildanim programom (https://github.com/bmatjaz/testnaNaloga/tree/master/Naloga/target Naloga-0.0.1-SNAPSHOT.jar)

Zažene se lokalni strežnik (localhost:8080) z H2 podatkovno bazo (http://localhost:8080/h2-console , geslo ni potrebno), ki ima kreirano tabelo PRESCRIPTION.

Tabelo se lahko napolni z klici /create (spodnji linki)

http://localhost:8080/create?medName=Nalgesin&dosage=500&unit=g&start_time=2019-02-08 12:04:27.883&end_time=2019-02-09 18:04:27.883&timing_hours_period=1

http://localhost:8080/create?medName=lekadol&dosage=100&unit=mg&start_time=2019-02-08 18:04:27.883&end_time=2019-02-09 22:04:27.883&timing_hours_period=2

http://localhost:8080/create?medName=Aspirin&dosage=100&unit=mg&start_time=2019-02-08 02:04:27.883&end_time=2019-02-09 22:23:27.883&timing_hours_period=3

http://localhost:8080/create?medName=Aspirin&dosage=100&unit=mg&start_time=2019-02-08 02:04:27.883&end_time=2019-02-09 12:23:27.883&timing_hours_period=1

