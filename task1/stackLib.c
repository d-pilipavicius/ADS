#include <stdlib.h>
#include <limits.h>

#define TRUE (1==1)
#define FALSE (!TRUE)

#define NO_ERRORS -1
#define ERROR_NULL_STACK_POINTER -2
#define ERROR_NULL_STACK_HOLDER_POINTER -3

typedef struct Stack
{
    int value;
    void *next;
}Stack;

Stack* _sCreate()
{
    Stack *stk = (Stack*) malloc(sizeof(Stack));
    stk->next = NULL;
    stk->value = 0;
    return stk;
}

unsigned int _sCount(Stack *stk)
{
    if(stk == NULL)
        return 0;

    unsigned int i = 0;
    while(stk->next != NULL)
    {
        stk = (Stack*) stk->next;
        i++;
    }
    return i;
}

int _sFull(Stack *stk)
{
    if(stk == NULL)
    {
        return ERROR_NULL_STACK_POINTER;
    }
    if(_sCount(stk) == UINT_MAX)
        return TRUE;
    else
        return FALSE;
}

int _sEmpty(Stack *stk)
{
    if(stk == NULL)
        return ERROR_NULL_STACK_POINTER;
    if(_sCount(stk) == 0)
        return TRUE;
    else
        return FALSE;
}

int _sPush(Stack **stk, int value)
{
    if(stk == NULL)
        return ERROR_NULL_STACK_HOLDER_POINTER;
    if(*stk == NULL)
        return ERROR_NULL_STACK_POINTER;

    Stack *temp = (Stack*) malloc(sizeof(Stack));
    temp->value = value;
    temp->next = *stk;
    *stk = temp;

    return NO_ERRORS;
}

int _sPop(Stack **stk)
{
    if(stk == NULL)
        return 0;
    if(*stk == NULL)
        return 0;
    if(_sEmpty(*stk) == TRUE)
        return 0;

    int value;
    Stack *temp = *stk;
    value = temp->value;
    *stk = temp->next;
    free((Stack*) temp);

    return value;
}

int _sDone(Stack **stk)
{
    if(stk == NULL)
        return ERROR_NULL_STACK_HOLDER_POINTER;
    if(*stk == NULL)
        return ERROR_NULL_STACK_POINTER;

    Stack *temp;
    unsigned int sCount = _sCount(*stk);

    for(unsigned int i = 0; i < sCount+1; ++i)
    {
        temp = *stk;
        *stk = temp->next;
        free((Stack*) temp);
    }
    return NO_ERRORS;
}

Stack* _sClone(Stack *stk)
{
    if(stk == NULL)
        return NULL;

    unsigned int sCount = _sCount(stk);
    int *values = (int*) malloc(sCount*sizeof(int));
    Stack *newClone = _sCreate();

    for(unsigned int i = 0; i < sCount; ++i)
    {
        values[i] = stk->value;
        stk = (Stack*) stk->next;
    }
    for(unsigned int i = 0; i < sCount; ++i)
    {
        _sPush(&newClone, values[sCount-i-1]);
    }
    free((int*) values);

    return newClone;
}
