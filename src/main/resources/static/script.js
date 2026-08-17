const API_URL = "/api/employees";
const DEPARTMENT_URL = "/api/departments";

let employees = [];
let departments = [];


/* =========================
   LOAD DATA
========================= */

window.onload = function () {
    loadEmployees();
    loadDepartments();
};


/* =========================
   EMPLOYEES
========================= */

function loadEmployees() {

    fetch(API_URL)
        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to load employees");
            }

            return response.json();
        })

        .then(data => {

            employees = data;

            displayEmployees(employees);
            updateDashboard();

        })

        .catch(error => {

            console.error(error);

            alert("Unable to load employees. Please check that Spring Boot is running.");

        });
}


/* =========================
   DISPLAY EMPLOYEES
========================= */

function displayEmployees(data) {

    const table = document.getElementById("employeeTable");

    table.innerHTML = "";

    data.forEach(employee => {

        const row = document.createElement("tr");

        row.innerHTML = `

            <td>${employee.employeeId}</td>

            <td>${employee.employeeName}</td>

            <td>${employee.email}</td>

            <td>${employee.phone}</td>

            <td>${employee.designation}</td>

            <td>₹${employee.salary}</td>

            <td>
                ${employee.department
                    ? employee.department.departmentName
                    : "N/A"}
            </td>

            <td>
                <span class="${
                    employee.status === "ACTIVE"
                    ? "status-active"
                    : "status-leave"
                }">
                    ${employee.status}
                </span>
            </td>

            <td>

                <button
                    class="edit-btn"
                    onclick="editEmployee(${employee.employeeId})">
                    Edit
                </button>

                <button
                    class="delete-btn"
                    onclick="deleteEmployee(${employee.employeeId})">
                    Delete
                </button>

            </td>
        `;

        table.appendChild(row);
    });
}


/* =========================
   DASHBOARD
========================= */

function updateDashboard() {

    document.getElementById("totalEmployees").innerText =
        employees.length;

    document.getElementById("activeEmployees").innerText =
        employees.filter(e => e.status === "ACTIVE").length;

    document.getElementById("leaveEmployees").innerText =
        employees.filter(e => e.status === "ON_LEAVE").length;
}


/* =========================
   DEPARTMENTS
========================= */

function loadDepartments() {

    fetch(DEPARTMENT_URL)

        .then(response => response.json())

        .then(data => {

            departments = data;

            const departmentSelect =
                document.getElementById("department");

            const filter =
                document.getElementById("departmentFilter");

            data.forEach(department => {

                const option1 = document.createElement("option");

                option1.value = department.departmentId;

                option1.textContent =
                    department.departmentName;

                departmentSelect.appendChild(option1);


                const option2 = document.createElement("option");

                option2.value = department.departmentId;

                option2.textContent =
                    department.departmentName;

                filter.appendChild(option2);

            });

        })

        .catch(error => {

            console.error("Department loading error:", error);

        });
}


/* =========================
   ADD FORM
========================= */

function openAddForm() {

    document.getElementById("employeeForm").style.display = "block";

    document.getElementById("formTitle").innerText =
        "Add Employee";

    clearForm();
}


/* =========================
   CLOSE FORM
========================= */

function closeForm() {

    document.getElementById("employeeForm").style.display = "none";

    clearForm();
}


/* =========================
   CLEAR FORM
========================= */

function clearForm() {

    document.getElementById("employeeId").value = "";

    document.getElementById("employeeName").value = "";

    document.getElementById("email").value = "";

    document.getElementById("phone").value = "";

    document.getElementById("designation").value = "";

    document.getElementById("salary").value = "";

    document.getElementById("status").value = "ACTIVE";

    document.getElementById("department").value = "";
}


/* =========================
   SAVE EMPLOYEE
========================= */

function saveEmployee() {

    const id =
        document.getElementById("employeeId").value;

    const departmentId =
        document.getElementById("department").value;

    const employee = {

        employeeName:
            document.getElementById("employeeName").value,

        email:
            document.getElementById("email").value,

        phone:
            document.getElementById("phone").value,

        designation:
            document.getElementById("designation").value,

        salary:
            parseFloat(document.getElementById("salary").value),

        status:
            document.getElementById("status").value,

        department: {
            departmentId: parseInt(departmentId)
        }
    };


    const method = id ? "PUT" : "POST";

    const url = id
        ? `${API_URL}/${id}`
        : API_URL;


    fetch(url, {

        method: method,

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(employee)

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Save failed");
        }

        return response.json();

    })

    .then(() => {

        alert(
            id
            ? "Employee updated successfully!"
            : "Employee added successfully!"
        );

        closeForm();

        loadEmployees();

    })

    .catch(error => {

        console.error(error);

        alert("Unable to save employee.");

    });
}


/* =========================
   EDIT EMPLOYEE
========================= */

function editEmployee(id) {

    const employee =
        employees.find(e => e.employeeId === id);

    if (!employee) {
        return;
    }


    document.getElementById("employeeForm").style.display =
        "block";

    document.getElementById("formTitle").innerText =
        "Update Employee";


    document.getElementById("employeeId").value =
        employee.employeeId;

    document.getElementById("employeeName").value =
        employee.employeeName;

    document.getElementById("email").value =
        employee.email;

    document.getElementById("phone").value =
        employee.phone;

    document.getElementById("designation").value =
        employee.designation;

    document.getElementById("salary").value =
        employee.salary;

    document.getElementById("status").value =
        employee.status;

    document.getElementById("department").value =
        employee.department.departmentId;
}


/* =========================
   DELETE EMPLOYEE
========================= */

function deleteEmployee(id) {

    const confirmDelete =
        confirm("Are you sure you want to delete this employee?");

    if (!confirmDelete) {
        return;
    }


    fetch(`${API_URL}/${id}`, {

        method: "DELETE"

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Delete failed");
        }

        alert("Employee deleted successfully!");

        loadEmployees();

    })

    .catch(error => {

        console.error(error);

        alert("Unable to delete employee.");

    });
}


/* =========================
   SEARCH
========================= */

function searchEmployees() {

    const search =
        document.getElementById("searchInput")
        .value
        .toLowerCase();


    const filtered =
        employees.filter(employee =>

            employee.employeeName
                .toLowerCase()
                .includes(search)

            ||

            employee.email
                .toLowerCase()
                .includes(search)

            ||

            employee.designation
                .toLowerCase()
                .includes(search)
        );


    displayEmployees(filtered);
}


/* =========================
   DEPARTMENT FILTER
========================= */

function filterEmployees() {

    const departmentId =
        document.getElementById("departmentFilter").value;


    if (departmentId === "") {

        displayEmployees(employees);

        return;
    }


    const filtered =
        employees.filter(employee =>

            employee.department &&
            employee.department.departmentId ==
            departmentId
        );


    displayEmployees(filtered);
}