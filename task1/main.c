#include "stackLib.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <assert.h>

void popAndPrintStack(Stack **stk)
{
    unsigned int sCount = _sCount(*stk);
    for(unsigned int i = 0; i < sCount; ++i)
    {
        printf("%d elementas: %d\n", sCount-i, _sPop(stk));
    }
}

void printStackSize(Stack *stk, char *stackID)
{
    if(_sEmpty(stk) == TRUE)
        printf("%s stack is empty.\n", stackID);
    else if(_sFull(stk) == TRUE)
        printf("%s stack if full.\n", stackID);
    else if(_sEmpty(stk) == ERROR_NULL_STACK_POINTER)
        printf("%s stack is not declared!\n", stackID);
    else
        printf("%s stack is neither full or empty, it has %d elements.\n", stackID, _sCount(stk));
}

int main()
{
    Stack *stk, *stk2;
    //Creating an empty stack
    stk = _sCreate();
    printStackSize(stk, "First");
    //Create a stack with 100 random elements
    srand(time(NULL));
    for(int i = 0; i < 100; ++i)
    {
        _sPush(&stk, rand());
    }
    printStackSize(stk, "First");
    //Duplicate stack
    stk2 = _sClone(stk);
    printStackSize(stk2, "Second");
    //Print out both stacks side by side
    popAndPrintStack(&stk);
    popAndPrintStack(&stk2);
    printStackSize(stk, "First");
    printStackSize(stk2, "Second");
    //Filling the first stack with 100 random elements
    for(int i = 0; i < 100; ++i)
    {
        _sPush(&stk, rand());
    }
    printStackSize(stk, "First");
    printStackSize(stk2, "Second");
    //Clearing stacks
    _sDone(&stk);
    _sDone(&stk2);
    //Checking if stacks did clear
    printStackSize(stk, "First");
    printStackSize(stk2, "Second");
    return 0;
}
