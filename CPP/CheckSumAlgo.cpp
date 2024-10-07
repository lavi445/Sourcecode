#include <iostream>
#include <cstring>
using namespace std;

int main() {
    char a[20], b[20];
    char sum[20], complement[20];
    int i, length;

    cout << "Enter binary string:\n";
    cin >> a;
    cout << "Enter second binary string:\n";
    cin >> b;

    if (strlen(a) == strlen(b)) {
        length = strlen(a);
        char carry = '0';

        for (i = length - 1; i >= 0; i--) {
            // Binary addition logic
            if (a[i] == '0' && b[i] == '0' && carry == '0') {
                sum[i] = '0';
                carry = '0';
            }
            else if (a[i] == '0' && b[i] == '0' && carry == '1') {
                sum[i] = '1';
                carry = '0';
            }
            else if (a[i] == '0' && b[i] == '1' && carry == '0') {
                sum[i] = '1';
                carry = '0';
            }
            else if (a[i] == '0' && b[i] == '1' && carry == '1') {
                sum[i] = '0';
                carry = '1';
            }
            else if (a[i] == '1' && b[i] == '0' && carry == '0') {
                sum[i] = '1';
                carry = '0';
            }
            else if (a[i] == '1' && b[i] == '0' && carry == '1') {
                sum[i] = '0';
                carry = '1';
            }
            else if (a[i] == '1' && b[i] == '1' && carry == '0') {
                sum[i] = '0';
                carry = '1';
            }
            else if (a[i] == '1' && b[i] == '1' && carry == '1') {
                sum[i] = '1';
                carry = '1';
            }
            else
                break;
        }

        cout << "\nSum = " << carry << sum;
        
        // Checksum calculation
        for (i = 0; i < length; i++) {
            complement[i] = (sum[i] == '0') ? '1' : '0';
        }

        carry = (carry == '1') ? '0' : '1';
        cout << "\nChecksum = " << carry << complement;
    }
    else {
        cout << "\nWrong input string";
    }

    return 0;
}
