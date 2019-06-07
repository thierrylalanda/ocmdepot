/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var applicationServerPublicKey = 'BHn27kt4X-J_X2k1k84Ael4zCybGp6yKH320AvisXLKVnxvo-u5xWICxnzEzqoALq4N3jDXqttuKMbIfw7BkRf0';
    if ('Notification' in window && navigator.serviceWorker) {
        // Display the UI to let the user toggle notifications
        displayNotificationWithAction();
    }

    if (Notification.permission === "granted") {
        /* do our magic */
    } else if (Notification.permission === "blocked") {
        /* the user has previously denied push. Can't reprompt. */
    } else {
        Notification.requestPermission(function (status) {
            console.log('Notification permission status:', status);
        });
    }

    if ('serviceWorker' in navigator && 'PushManager' in window) {
        console.log('Service Worker and Push is supported');

        navigator.serviceWorker.register('sw.js')
                .then(function (swReg) {
                    console.log('Service Worker is registered', swReg);

                    swRegistration = swReg;
                })
                .catch(function (error) {
                    console.error('Service Worker Error', error);
                });
    } else {
        console.warn('Push messaging is not supported');
        pushButton.textContent = 'Push Not Supported';
    }

    function initializeUI() {
        // Set the initial subscription value
        swRegistration.pushManager.getSubscription()
                .then(function (subscription) {
                    isSubscribed = !(subscription === null);

                    if (isSubscribed) {
                        console.log('User IS subscribed.');
                    } else {
                        console.log('User is NOT subscribed.');
                    }

                    updateBtn();
                });
    }

    function updateBtn() {
        if (isSubscribed) {
            pushButton.textContent = 'Disable Push Messaging';
        } else {
            pushButton.textContent = 'Enable Push Messaging';
        }

        pushButton.disabled = false;
    }
});
function displayNotification() {
    if (Notification.permission == 'granted') {
        navigator.serviceWorker.getRegistration().then(function (reg) {
            var options = {
                body: 'Here is a notification body!',
                icon: 'images/example.png',
                vibrate: [100, 50, 100],
                data: {
                    dateOfArrival: Date.now(),
                    primaryKey: 1
                }
            };
            reg.showNotification('Hello world!', options);
        });
    }
}

function displayNotificationWithAction() {
    if (Notification.permission === 'granted') {
        navigator.serviceWorker.getRegistration().then(function (reg) {
            var options = {
                body: 'Here is a notification body!',
                icon: 'images/example.png',
                vibrate: [100, 50, 100],
                data: {
                    dateOfArrival: Date.now(),
                    primaryKey: 1
                },
                actions: [
                    {action: 'explore', title: 'Explore this new world',
                        icon: '/assets/images/1.png'},
                    {action: 'close', title: 'Close notification',
                        icon: '/assets/images/1.png'}
                ]
            };
            reg.showNotification('Hello world!', options);
        });
    }
}



