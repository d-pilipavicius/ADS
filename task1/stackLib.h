#ifndef STACKDEF
#define STACKDEF

typedef struct Stack
{
    int value;
    void *next;
}Stack;

#define TRUE (1==1)
#define FALSE (!TRUE)

#define NO_ERRORS -1
#define ERROR_NULL_STACK_POINTER -2
#define ERROR_NULL_STACK_HOLDER_POINTER -3

Stack* _sCreate();
unsigned int _sCount(Stack *stk);
int _sFull(Stack *stk);
int _sEmpty(Stack *stk);
int _sPush(Stack **stk, int value);
int _sPop(Stack **stk);
int _sDone(Stack **stk);
Stack* _sClone(Stack *stk);
#endif
