#include<stdio.h>

void bubbleSort(int arr[],int n){
int temp;
for(int i=0;i<n;i++){
for(int j=0;j<(n-1);j++)
{
if(arr[j]>arr[j+1]){
temp=arr[j];
arr[j]=arr[j+1];
arr[j+1]=temp;
}
}
}
}

void printArray(int arr[],int n){

for(int i=0;i<n;i++){
printf("%d",arr[i]);
printf("\n");
}
}

int main() {
int arr[] = {64, 25, 12, 22, 11};
int n = sizeof(arr)/sizeof(arr[0]);

printf("Array size is = %d",n);
printf("Original array:\n");
printArray(arr, n);
bubbleSort(arr, n);
printf("Sorted array:\n");
printArray(arr, n);
return 0;
}


