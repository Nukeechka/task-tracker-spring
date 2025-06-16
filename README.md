# Tasks API

Base URL: `/api/tasks`

## 📌 Endpoints

### 🟣 GET `/api/tasks`
**Description:** Get all tasks.  
**Response Example:**

```json
[
  {
    "id": 6,
    "title": "Task 11",
    "description": "Description for task 11",
    "priority": "LOW",
    "status": "CLOSED"
  },
  {
    "id": 7,
    "title": "Task 2",
    "description": "Description for task 2",
    "priority": "LOW",
    "status": "OPEN"
  }
]
```

---

### 🟣 GET `/api/tasks/{id}`
**Description:** Get a task by its ID.  
**Path Parameter:** `id` — Task ID  
**Response:** Returns the task with the specified ID.

---

### 🟢 POST `/api/tasks`
**Description:** Create a new task.  
**Body:** JSON object with task details.  
**Response:** Returns the created task with its ID.

---

### 🟠 PUT `/api/tasks/{id}`
**Description:** Update an existing task.  
**Path Parameter:** `id` — Task ID  
**Body:** JSON object with updated task details.  
**Response:** Returns the updated task.

---

### 🔴 DELETE `/api/tasks/{id}`
**Description:** Delete a task by its ID.  
**Path Parameter:** `id` — Task ID  
**Response:** Status 204 No Content if successful.

### Errors
**Response Example:**

```json
{
	"message": "Task not found with ID 1",
	"timestamp": 1750071182918
}
```
