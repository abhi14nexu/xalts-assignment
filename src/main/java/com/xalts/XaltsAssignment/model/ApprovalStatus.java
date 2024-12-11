package com.xalts.XaltsAssignment.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToMany
    @JoinTable(
        name = "task_approvers",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> approvers; // Store the 3 selected approvers

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskApproval> approvals;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt;

    @Column
    private Integer requiredApprovals = 3; // Fixed requirement of 3 approvals

    public boolean isFullyApproved() {
        if (approvals == null) return false;
        long approvedCount = approvals.stream()
            .filter(approval -> approval.getStatus() == ApprovalStatus.APPROVED)
            .count();
        return approvedCount >= requiredApprovals;
    }

    public boolean canBeApprovedBy(User user) {
        return approvers.contains(user) && 
               approvals.stream()
                   .noneMatch(approval -> approval.getApprover().equals(user));
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        // Update task status based on approvals
        if (isFullyApproved()) {
            this.status = TaskStatus.COMPLETED;
        }
    }

    public void addApprover(User approver) {
        if (approvers.size() < requiredApprovals) {
            approvers.add(approver);
        } else {
            throw new IllegalStateException("Cannot add more than " + requiredApprovals + " approvers");
        }
    }

    public void addApproval(TaskApproval approval) {
        if (!canBeApprovedBy(approval.getApprover())) {
            throw new IllegalStateException("This user is not authorized to approve this task or has already approved");
        }
        approvals.add(approval);
        checkAndUpdateStatus();
    }

    private void checkAndUpdateStatus() {
        if (isFullyApproved()) {
            this.status = TaskStatus.COMPLETED;
        } else if (!approvals.isEmpty()) {
            this.status = TaskStatus.IN_PROGRESS;
        }
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}

// Additional required enums
public enum ApprovalStatus {
    PENDING,
    APPROVED,
    REJECTED
}
