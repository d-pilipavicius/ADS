# Stack data structure

## Declaration of a Stack type variable

### Stack *_stackName;
- It is important to note that the Stack data type variable must be declared as a pointer (Stack*).

## Defined codes

   ### TRUE  
   - used for logical operations as a 1.
   ### FALSE
   - used for logical operations as a 0.
   ### NO_ERRORS
   - an error code used to show no errors occured.
   ### ERROR_NULL_STACK_POINTER
   - an error code which can occur if a NULL Stack pointer was passed down via parameters.
   ### ERROR_NULL_STACK_HOLDER_POINTER
   - an error code which can occur if a NULL pointer to a variable that is holding the address to a Stack type variable is passed down via parameters.

## Stack functions

### Stack* _sCreate()
    - This returns an address to an empty stack.

### unsigned int _sCount(Stack *stk)
    - The parameter passed down is meant to be the Stack itself.
    - The function can return numbers in the range [0, UINT_MAX].
    - If the passed down Stack is a NULL pointer, the function will return a 0.

### int _sFull(Stack *stk)
    - The parameter passed down is meant to be the Stack itself.
    - This function returns TRUE if the Stack is full, and FALSE if it is not full.
    - If the passed down Stack is a NULL pointer, the function will return ERROR_NULL_STACK_POINTER.

### int _sEmpty(Stack *stk)
    - The parameter passed down is meant to be the Stack itself.
    - This function returns TRUE if the Stack is empty, and FALSE if it is not empty.
    - If the passed down Stack is a NULL pointer, the function will return ERROR_NULL_STACK_POINTER.

### int _sPush(Stack **stk, int value)
    - The parameter passed down is meant to be a pointer to the location of the Stack pointer, not the Stack itself.
    - "value" will be pushed to the top of the provided Stack, increasing the size of the Stack.
    - This function creates a new segment of the Stack, changing the top value in the provided address.
    - If the passed down address to the Stack is a NULL pointer, the function will return ERROR_NULL_STACK_HOLDER_POINTER.
    - If the passed down Stack inside the address is a NULL pointer, the function will return ERROR_NULL_STACK_POINTER.
    - If no errors occur while pushing an element, the function will return NO_ERRORS. 

### int _sPop(Stack **stk)
    - The parameter passed down is meant to be a pointer to the location of the Stack pointer, not the Stack itself.
    - This function frees the top element of the Stack, changing the top value in the provided address.
    - This function returns the value, which the top element was holding.
    - If the passed down address to the Stack is a NULL pointer, the function will return 0.
    - If the passed down Stack inside the address is a NULL pointer, the function will return 0.

### int _sDone(Stack **stk)
    - The parameter passed down is meant to be a pointer to the location of the Stack pointer, not the Stack itself.
    - This function destroys the Stack, clearing every element in it and changing the pointer to the Stack to NULL in the provided address.
    - If the passed down address to the Stack is a NULL pointer, the function will return ERROR_NULL_STACK_HOLDER_POINTER.
    - If the passed down Stack inside the address is a NULL pointer, the function will return ERROR_NULL_STACK_POINTER.
    - If no errors occur while freeing the Stack, the function will return NO_ERRORS. 

### Stack* _sClone(Stack *stk)
    - The parameter passed down is meant to be the Stack itself.
    - The function will create an identical Stack to the provided one in a differend address, returning that address via its name.
    - If the passed down Stack is a NULL pointer, the function will return a ERROR_NULL_STACK_POINTER.
