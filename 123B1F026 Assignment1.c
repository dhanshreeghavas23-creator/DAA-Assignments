#include <stdio.h>

void mergeSort(int arr[],int left,int right){
    if(left<right){
        int mid=left+(right-left)/2;
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);

        merge(arr,left,mid,right);
    }
}

void merge(int arr[],int left,int mid,int right){
    int i,j,k;
    int n1=mid-left+1;
    int n2=right-mid;

    int leftTemp[n1];
    int rightTemp[n2];

    for(int i=0;i<n1;i++){
        leftTemp=arr[left+i];
    }
    for(int j=0;j<n2;j++){
        rightTemp=arr[mid+1+j];
    }

    i=0;j=0;k=left;
    while(i<n1&&j<n2){
        if(leftTemp[i]<rightTemp[j]){
            arr[k]=leftTemp[i];
            i++;
        }
        else{
            arr[k]=rightTemp[j];
            j++;
        }
        k++;
    }

    while(i<n1){
        arr[k]=leftTemp[i];
        i++;
        k++;
    }

    while(j<n2){
        arr[k]=rightTemp[i];
        j++;
        k++;
    }
}

int main(){
    int arr={21,45,12,34,67,43,89};
    int n=sizeof(arr)/sizeof(arr[0]);

    mergeSort(arr,0,n-1);

    for(int i=0;i<n;i++){
        printf("%d",&arr[i]);
    }
    return 0;
}
