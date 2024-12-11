# Multi-Signature Task Management System

A Spring Boot application that implements a multi-signature task approval system with real-time notifications.

## Features

### User Management
- User registration and login system
- Unique internal login ID generation
- PostgreSQL database integration for user data storage

### Task Management
- Create tasks with title, description, and status
- Task status tracking (PENDING, IN_PROGRESS, APPROVED, REJECTED, CANCELLED)
- Multi-signature approval process
- Comments functionality on tasks

### Approval System
- Three-user approval requirement
- Real-time notifications via WebSocket
- Email notifications for task creation and approvals
- Dropdown selection for approvers

### API Integration
- RESTful API architecture
- CORS enabled for web integration
- WebSocket support for real-time updates

## Technical Stack

- Java 17
- Spring Boot 3.x
- PostgreSQL Database
- Spring Data JPA
- Spring Security
- WebSocket (STOMP)
- JavaMail for email notifications

## API Endpoints

### Authentication
- POST /api/auth/signup - User registration
- POST /api/auth/login - User login

### Task Management
- POST /api/tasks - Create new task
- GET /api/tasks - List all tasks
- GET /api/tasks/{id} - Get task details
- POST /api/tasks/{id}/approve - Approve task
- POST /api/tasks/{id}/comment - Add comment to task

### Multi-Signature Process
- GET /api/multi-signature/tasks/pending - Get pending approval tasks
- GET /api/multi-signature/tasks/{taskId}/status - Get task approval status
- GET /api/multi-signature/tasks/{taskId}/approvers - Get task approvers
- POST /api/multi-signature/tasks/{taskId}/approve - Approve task

## Setup Instructions

1. Database Setup
```sql
CREATE DATABASE xalts_task_management;
```

2. Application Properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/xalts_task_management
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build and Run
```bash
./mvnw clean install
./mvnw spring-boot:run
```

## WebSocket Integration

Connect to WebSocket endpoint:
```javascript
const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

// Subscribe to task updates
stompClient.subscribe('/topic/task/{taskId}/approval', function(notification) {
	console.log(JSON.parse(notification.body));
});
```

## Security Considerations

- Password encryption using BCrypt
- JWT-based authentication
- CORS configuration for secure web integration
- Input validation and sanitization

## Error Handling

The API uses standard HTTP status codes and returns detailed error messages in the following format:
```json
{
	"success": false,
	"message": "Error description",
	"data": null
}
```