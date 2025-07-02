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

    // Function to filter logs by category
    function filterLogs(logs, category) {
        switch(category) {
            case 'controller':
                return logs.filter(log => log.toLowerCase().includes('controller'));
            case 'service':
                return logs.filter(log => log.toLowerCase().includes('service'));
            case 'exception':
                return logs.filter(log => log.toLowerCase().includes('exception') || log.toLowerCase().includes('error') || log.toLowerCase().includes('handling'));
            case 'database':
                return logs.filter(log => log.toLowerCase().includes('database'));
            case 'scheduled':
                return logs.filter(log => log.toLowerCase().includes('terjadwal') || log.toLowerCase().includes('scheduled'));
            default:
                return logs;
        }
    }

    // Fetch and display logs with optional filter
    function fetchAndDisplayLogs(category) {
        fetch('/api/logs')
            .then(response => response.json())
            .then(data => {
                const logsContainer = document.getElementById('logs-container');
                logsContainer.innerHTML = '';
                let logs = data.logs;
                if (category) {
                    logs = filterLogs(logs, category);
                }
                if (logs.length === 0) {
                    const exampleLogs = getExampleLog(category);
                    if (exampleLogs.length > 0) {
                        logsContainer.innerHTML = '<p>No logs found for this category. Example log:</p>';
                        exampleLogs.forEach(log => {
                            const logEntry = document.createElement('div');
                            logEntry.className = 'log-entry example-log';
                            logEntry.textContent = log;
                            logsContainer.appendChild(logEntry);
                        });
                    } else {
                        logsContainer.innerHTML = '<p>No logs found for this category.</p>';
                    }
                } else {
                    logs.forEach(log => {
                        const logEntry = document.createElement('div');
                        logEntry.className = 'log-entry';
                        logEntry.textContent = log;
                        logsContainer.appendChild(logEntry);
                    });
                }
            })
            .catch(error => {
                console.error('Error fetching logs:', error);
                alert('Failed to refresh logs');
            });
    }

    // Event listeners for filter buttons
    const filterButtons = document.querySelectorAll('.log-filter');
    filterButtons.forEach(button => {
        button.addEventListener('click', function() {
            const category = this.getAttribute('data-filter');
            fetchAndDisplayLogs(category);
        });
    });

    // Event listener for refresh all logs
    const refreshLogsButton = document.getElementById('refresh-logs');
    if (refreshLogsButton) {
        refreshLogsButton.addEventListener('click', function() {
            fetchAndDisplayLogs();
        });
    }
});

function getExampleLog(category) {
    switch(category) {
        case 'controller':
            return [
                '2024-06-08 10:00:00 [http-nio-8080-exec-1] INFO  com.example.errorhandling.controller.MainController - Menerima request di Controller: Home page'
            ];
        case 'service':
            return [
                '2024-06-08 10:00:01 [http-nio-8080-exec-1] INFO  com.example.errorhandling.controller.ExampleService - Eksekusi logika bisnis di service'
            ];
        case 'exception':
            return [
                '2024-06-08 10:00:02 [http-nio-8080-exec-1] ERROR com.example.errorhandling.handler.GlobalExceptionHandler - Handling Bad Request Exception: Invalid request parameters provided.'
            ];
        case 'database':
            return [
                '2024-06-08 10:00:03 [http-nio-8080-exec-1] INFO  com.example.errorhandling.controller.ErrorDemoController - Simulasi interaksi dengan database: SELECT * FROM users'
            ];
        case 'scheduled':
            return [
                '2024-06-08 10:01:00 [scheduling-1] INFO  com.example.errorhandling.ErrorhandlingApplication - Aktivitas terjadwal dieksekusi setiap 1 menit'
            ];
        default:
            return [];
    }
}
