// Main JavaScript for Error Handling Demo Website
document.addEventListener('DOMContentLoaded', function() {
    console.log('Error Handling Demo Website Loaded');
    
    // Function to trigger error examples
    const errorButtons = document.querySelectorAll('.trigger-error');
    if (errorButtons.length > 0) {
        errorButtons.forEach(button => {
            button.addEventListener('click', function() {
                const errorType = this.getAttribute('data-error-type');
                fetch(`/api/error/${errorType}`, {
                    method: 'GET'
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    alert(`Error triggered successfully: ${data.message}`);
                })
                .catch(error => {
                    alert(`Error occurred: ${error.message}`);
                });
            });
        });
    }

    // Function to refresh logs
    const refreshLogsButton = document.getElementById('refresh-logs');
    if (refreshLogsButton) {
        refreshLogsButton.addEventListener('click', function() {
            fetch('/api/logs')
                .then(response => response.json())
                .then(data => {
                    const logsContainer = document.getElementById('logs-container');
                    logsContainer.innerHTML = '';
                    data.logs.forEach(log => {
                        const logEntry = document.createElement('div');
                        logEntry.className = 'log-entry';
                        logEntry.textContent = log;
                        logsContainer.appendChild(logEntry);
                    });
                })
                .catch(error => {
                    console.error('Error fetching logs:', error);
                    alert('Failed to refresh logs');
                });
        });
    }
});
