#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    char *val;
    struct Node *prev;
    struct Node *next;
};

void insert(struct Node **head_ptr, char *val) {
    struct Node *newNode = malloc(sizeof(struct Node));
    if (newNode == NULL) return;

    newNode->val = val;
    newNode->next = NULL;
    newNode->prev = NULL;

    if (*head_ptr == NULL) {
        *head_ptr = newNode;
    } else {
        struct Node *temp = *head_ptr;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newNode;
        newNode->prev = temp;
    }
}

struct Node* find(struct Node *head, char *val) {
    struct Node *temp = head;
    while (temp != NULL) {
        if (strcmp(temp->val, val) == 0) {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}

void delete(struct Node **head_ref, char *val) {
    if (head_ref == NULL || *head_ref == NULL) {
        return;
    }
    struct Node *temp = *head_ref;
    while (temp != NULL) {
        if (strcmp(temp->val, val) == 0) {
            if (temp == *head_ref) {
                *head_ref = temp->next;
            } else {
                temp->prev->next = temp->next;
            }
            if (temp->next != NULL) {
                temp->next->prev = temp->prev;
            }
            free(temp);
            return;
        }
        temp = temp->next;
    }
}

int main() {
    struct Node *head = NULL;
    insert(&head, "1");
    insert(&head, "2");
    insert(&head, "3");
    
    delete(&head, "2");
    
    struct Node *found = find(head, "3");
    if (found != NULL) {
        printf("Found: %s\n", found->val);
    }
    
    return 0;
}