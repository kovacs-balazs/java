# Háromszög kerület kalkulátor

---

A program a beadott derékszögű háromszögek két oldalában átfogót számol, majd a háromszög kerületét rögzíti, kiíratáskor gyakoriság szerint csökkenő sorrendbe rendezi. A kerület számításnál a program 3 tizedesjegyre kerekít.

Használat
---
#### Indító parancs: 
```
python main.py
```
</br>
A program elindítása után a megnyíló GUI-ban kell megadni a két oldal méretét. A program mértékegység független, a bemeneti szövegekhez csak számok írhatóak. A felvétel gombra kattintva a megadott két oldalból Pitagorasz-tétellel kiszámolja az átfogót, majd felveszi a szótárba 3 tizedesjegyre kerekítve a háromszög kerületét. A listázás gombra kattintva kiírathatjuk a háromszögek kerületeinek szótárát gyakoriság szerint csökkenő sorrendbe rendezve.

#### Dependency
---
- [tkinter](https://docs.python.org/3/library/tkinter.html)
