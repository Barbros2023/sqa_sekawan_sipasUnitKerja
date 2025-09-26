# Playwright BDD – Unit Kerja Automation

## Initial Project Setup

* **Language:** Java  
* **Build System:** Gradle  
* **JDK:** Temurin 20 (Java 17+ also works)  
* **Gradle DSL:** Groovy  
* **Test Runner:** JUnit 5  

## Tools & Libraries Used

* **Playwright for Java** – browser automation  
* **JUnit 5** – test runner  
* **Cucumber (Gherkin BDD)** – BDD-style scenarios  
* **Page Object Model (POM)** – clean structure for pages  
* **Gradle** – build system  
* **GitHub Actions** – CI/CD pipeline  

## What This Project Does

This project automates **one end-to-end test case** for **Unit Kerja** on `demo.sipas.id/app/soho`:

1. Login with valid credentials  
2. Go to **Pengaturan** menu  
3. Open **Unit Kerja** sub menu  
4. Click **Tambah** to add a new unit  
5. Fill in **Finance35** and **f-35**  
6. Ensure the **Aktif** checkbox is handled (default checked)  
7. Save the new unit  
8. Confirm with **OK**  
9. Switch to **Tampilan Tabel** view  
10. Search for the new unit by name to verify the created unit

## Other Potential Test Cases

### 1. Edit an existing Unit Kerja
**Detailed Steps**:
1. Go to **Unit Kerja** page and switch to **Tampilan Tabel**.  
2. Search for **Finance35** in the table search box and press Enter.  
3. Wait until the row with **Finance35** appears.  
4. Open the edit form for that row (double-click).  
5. Update the `Nama` field to **Finance16**.  
6. Update the `Kode` field to **F-16**.  
7. Set **Aktif** checkbox to `false` (uncheck it).  
8. Save and confirm (OK).  
9. Search for **Finance16** in table view.  
10. Verify that **Finance16** appears in the list.  

---

### 2. Delete an existing Unit Kerja
**Detailed Steps**:
1. Go to **Tampilan Tabel**.  
2. Search for **Finance35** in the table and press Enter.  
3. Wait until the row with **Finance35** appears.  
4. Select that row (double-click).  
5. Click the **Hapus** button.  
6. Confirm the deletion (Yes/OK).  
7. Re-run the search for **Finance35**.  
8. Verify that no row with **Finance35** is found in the list.  

---

### 3. Search for a Unit Kerja
**Detailed Steps**:
1. Go to **Tampilan Tabel**.  
2. Enter **Finance35** in the search field and press Enter.  
3. Wait until rows refresh.  
4. Verify that a row containing **Finance35** appears in the list.  

and more...
