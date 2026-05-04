// Admin Dashboard Logic

// ===== Toast Notification System =====
function showToast(message, type = 'success') {
    const container = document.getElementById('toastContainer');
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    
    const icons = { success: '✅', error: '❌', warning: '⚠️' };
    toast.innerHTML = `<span class="toast-icon">${icons[type] || '💬'}</span><span>${message}</span>`;
    
    container.appendChild(toast);
    
    setTimeout(() => {
        toast.classList.add('toast-out');
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// ===== Utility: Format time to 12h =====
function formatTime12h(timeStr) {
    const parts = timeStr.split(':');
    let h = parseInt(parts[0]);
    const m = parts[1];
    const isPM = h >= 12;
    h = h % 12 || 12;
    return `${h}:${m} ${isPM ? 'PM' : 'AM'}`;
}

// ===== Utility: Skeleton Loading =====
function showTableSkeleton(tbody, cols, rows = 5) {
    const widths = ['w-20', 'w-30', 'w-15', 'w-10', 'w-20'];
    let html = '';
    for (let i = 0; i < rows; i++) {
        html += '<tr>';
        for (let j = 0; j < cols; j++) {
            html += `<td><div class="skeleton-cell ${widths[j % widths.length]}"></div></td>`;
        }
        html += '</tr>';
    }
    tbody.innerHTML = html;
}

// ===== Utility: Empty State =====
function showEmptyState(tbody, cols, icon, text, sub) {
    tbody.innerHTML = `<tr><td colspan="${cols}">
        <div class="empty-state">
            <div class="empty-state-icon">${icon}</div>
            <div class="empty-state-text">${text}</div>
            ${sub ? `<div class="empty-state-sub">${sub}</div>` : ''}
        </div>
    </td></tr>`;
}

// ===== Utility: Percentage color class =====
function pctClass(pct) {
    if (pct >= 75) return 'pct-high';
    if (pct >= 50) return 'pct-mid';
    return 'pct-low';
}

// ===== Utility: Button loading state =====
function setBtnLoading(btn, loading) {
    if (loading) {
        btn.disabled = true;
        btn.dataset.originalText = btn.querySelector('.btn-text')?.innerText || btn.innerText;
        const textEl = btn.querySelector('.btn-text');
        if (textEl) textEl.innerText = 'Loading...';
        const spinner = document.createElement('span');
        spinner.className = 'btn-spinner';
        btn.prepend(spinner);
    } else {
        btn.disabled = false;
        const spinner = btn.querySelector('.btn-spinner');
        if (spinner) spinner.remove();
        const textEl = btn.querySelector('.btn-text');
        if (textEl) textEl.innerText = btn.dataset.originalText || 'Save';
    }
}


(async function initAdmin() {
    let user = JSON.parse(sessionStorage.getItem('user'));
    if(!user) {
        try {
            const res = await fetch('/api/users/me');
            if(res.ok) {
                user = await res.json();
                sessionStorage.setItem('user', JSON.stringify(user));
            } else {
                window.location.href = '/index.html';
                return;
            }
        } catch(e) { window.location.href = '/index.html'; return; }
    }
    
    if(!user || user.role !== 'ROLE_ADMIN') {
        window.location.href = '/index.html';
        return;
    }

    // Navigation with smooth transitions
    document.querySelectorAll('.nav-link').forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            document.querySelectorAll('.nav-link').forEach(l => l.classList.remove('active'));
            document.querySelectorAll('.section-content').forEach(s => s.classList.add('section-hidden'));
            
            e.target.closest('.nav-link').classList.add('active');
            const target = e.target.closest('.nav-link').dataset.target;
            const section = document.getElementById(target);
            section.classList.remove('section-hidden');
            // Re-trigger animation
            section.style.animation = 'none';
            section.offsetHeight; // trigger reflow
            section.style.animation = 'fadeIn 0.3s ease';
        });
    });

// ===== Load Students with Skeleton =====
async function loadStudents() {
    const tbody = document.querySelector('#studentsTable tbody');
    showTableSkeleton(tbody, 4);
    
    const res = await fetch('/api/students');
    const data = await res.json();
    tbody.innerHTML = '';
    
    const filteredData = data.filter(s => s.sprno && s.sprno.startsWith('23CS'));
    
    if (filteredData.length === 0) {
        showEmptyState(tbody, 4, '👥', 'No students found', 'Click "+ Add Student" to get started');
        return;
    }
    
    filteredData.forEach(s => {
        tbody.innerHTML += `
            <tr>
                <td><strong>${s.sprno}</strong></td>
                <td>${s.firstName} ${s.lastName}</td>
                <td>${s.email}</td>
                <td><button class="btn-danger" onclick="deleteStudent('${s.sprno}')">Delete</button></td>
            </tr>
        `;
    });
}

// ===== Add Student Modal =====
window.openAddStudentModal = function() {
    document.getElementById('addStudentModal').classList.add('open');
    document.getElementById('newSprno').focus();
}

window.closeAddStudentModal = function() {
    document.getElementById('addStudentModal').classList.remove('open');
    document.getElementById('addStudentForm').reset();
    document.getElementById('newPassword').value = 'password123';
}

document.getElementById('addStudentForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const btn = document.getElementById('addStudentBtn');
    setBtnLoading(btn, true);

    const sprno = document.getElementById('newSprno').value.trim().toUpperCase();
    const firstName = document.getElementById('newFirstName').value.trim();
    const lastName = document.getElementById('newLastName').value.trim();
    const email = document.getElementById('newEmail').value.trim();
    const password = document.getElementById('newPassword').value.trim() || 'password123';

    try {
        const res = await fetch('/api/students', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ sprno, firstName, lastName, email, password })
        });
        
        if (res.ok) {
            showToast(`Student ${firstName} ${lastName} added successfully!`, 'success');
            closeAddStudentModal();
            loadStudents();
        } else {
            const err = await res.text();
            showToast(err || 'Failed to add student', 'error');
        }
    } catch (err) {
        showToast('Server error. Please try again.', 'error');
    }
    
    setBtnLoading(btn, false);
});

window.deleteStudent = async function(sprno) {
    if(confirm('Are you sure you want to delete this student?')) {
        await fetch(`/api/students/${sprno}`, {method: 'DELETE'});
        showToast('Student deleted', 'warning');
        loadStudents();
    }
}

// ===== Duration Modal =====
window.openDurationModal = async function() {
    document.getElementById('durationModal').classList.add('open');
    const res = await fetch('/api/settings/attendance-time');
    if(res.ok) {
        const data = await res.json();
        const formatTime = (t) => t.length > 5 ? t.substring(0, 5) : t;
        document.getElementById('durationStart').value = formatTime(data.startTime);
        document.getElementById('durationEnd').value = formatTime(data.endTime);
        document.getElementById('durationTodayOnly').checked = false;
    }
}

window.closeDurationModal = function() {
    document.getElementById('durationModal').classList.remove('open');
}

window.saveDuration = async function(e) {
    e.preventDefault();
    const btn = document.getElementById('saveDurationBtn');
    setBtnLoading(btn, true);

    const startTime = document.getElementById('durationStart').value + ":00";
    const endTime = document.getElementById('durationEnd').value + ":00";
    const forTodayOnly = document.getElementById('durationTodayOnly').checked;

    const res = await fetch('/api/settings/attendance-time', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({startTime, endTime, forTodayOnly})
    });
    
    if(res.ok) {
        showToast('Attendance duration updated!', 'success');
        setTimeout(closeDurationModal, 500);
    } else {
        showToast('Failed to save duration', 'error');
    }
    
    setBtnLoading(btn, false);
}

// ===== Daily Report with Skeleton =====
document.getElementById('dailyDate').valueAsDate = new Date();
document.getElementById('dailyDate').addEventListener('change', loadDaily);

async function loadDaily() {
    const tbody = document.querySelector('#dailyTable tbody');
    showTableSkeleton(tbody, 5);

    const d = document.getElementById('dailyDate').value;
    const res = await fetch(`/api/attendance/daily?date=${d}`);
    const data = await res.json();
    
    const stuRes = await fetch('/api/students');
    const allStudents = await stuRes.json();
    
    tbody.innerHTML = '';
    
    const filteredData = data.filter(a => a.sprno && a.sprno.startsWith('23CS'));
    const filteredAllStudents = allStudents.filter(s => s.sprno && s.sprno.startsWith('23CS'));
    
    const presentSprnos = new Set(filteredData.map(a => a.sprno));
    const presentCount = presentSprnos.size;
    const absentCount = filteredAllStudents.filter(s => !presentSprnos.has(s.sprno)).length;
    const totalCount = filteredAllStudents.length;

    document.getElementById('dailyPresentCount').innerText = presentCount;
    document.getElementById('dailyAbsentCount').innerText = absentCount;
    document.getElementById('dailyTotalCount').innerText = totalCount;

    if(filteredData.length === 0 && filteredAllStudents.length === 0) {
        showEmptyState(tbody, 5, '📋', 'No attendance records', 'No data available for this date');
        return;
    }
    
    // List present students
    filteredData.forEach(a => {
        const timeStr = a.markedAt ? formatTime12h(a.markedAt) : '-';
        tbody.innerHTML += `
            <tr>
                <td><strong>${a.sprno}</strong></td>
                <td>${a.studentName}</td>
                <td><span class="badge badge-success">Present</span></td>
                <td>${timeStr}</td>
                <td>${a.attendanceMarkedBy}</td>
            </tr>
        `;
    });
    
    // List absent students
    filteredAllStudents.forEach(s => {
        if(!presentSprnos.has(s.sprno)) {
            tbody.innerHTML += `
                <tr>
                    <td><strong>${s.sprno}</strong></td>
                    <td>${s.firstName} ${s.lastName}</td>
                    <td><span class="badge" style="background-color: #FEE2E2; color: #991B1B;">Absent</span></td>
                    <td>-</td>
                    <td>-</td>
                </tr>
            `;
        }
    });
}

// ===== Monthly Analytics with Color Coding =====
document.getElementById('monthlyDate').value = new Date().toISOString().slice(0,7);
document.getElementById('monthlyDate').addEventListener('change', loadMonthly);

let chartInstance = null;

async function loadMonthly() {
    const monthlyTbody = document.querySelector('#monthlyTable tbody');
    const alertTbody = document.querySelector('#alertTable tbody');
    showTableSkeleton(monthlyTbody, 4);
    showTableSkeleton(alertTbody, 4);

    const m = document.getElementById('monthlyDate').value;
    const [year, month] = m.split('-');
    
    const res1 = await fetch(`/api/attendance/monthly-report?year=${year}&month=${month}`);
    const reportData = await res1.json();
    
    monthlyTbody.innerHTML = '';
    alertTbody.innerHTML = '';
    
    const filteredReportData = reportData.filter(r => r.sprno && r.sprno.startsWith('23CS'));
    
    if (filteredReportData.length === 0) {
        showEmptyState(monthlyTbody, 4, '📈', 'No report data', 'No students found for this month');
        showEmptyState(alertTbody, 4, '🎉', 'No alerts!', 'All students have good attendance');
    } else {
        filteredReportData.forEach(r => {
            const row = `
                <tr>
                    <td><strong>${r.sprno}</strong></td>
                    <td>${r.firstName} ${r.lastName}</td>
                    <td>${r.presentDays}</td>
                    <td><span class="${pctClass(r.percentage)}">${r.percentage.toFixed(1)}%</span></td>
                </tr>
            `;
            monthlyTbody.innerHTML += row;
        });

        const below75 = filteredReportData.filter(r => r.percentage < 75).sort((a,b) => a.percentage - b.percentage);
        if (below75.length === 0) {
            showEmptyState(alertTbody, 4, '🎉', 'All clear!', 'No students below 75% attendance');
        } else {
            below75.forEach(r => {
                alertTbody.innerHTML += `
                    <tr>
                        <td><strong>${r.sprno}</strong></td>
                        <td>${r.firstName} ${r.lastName}</td>
                        <td>${r.presentDays}</td>
                        <td><span class="${pctClass(r.percentage)}">${r.percentage.toFixed(1)}%</span></td>
                    </tr>
                `;
            });
        }
    }

    // Load Graph
    const res2 = await fetch(`/api/attendance/monthly-graph?year=${year}&month=${month}`);
    const graphData = await res2.json();
    
    const labels = Object.keys(graphData).sort();
    const counts = labels.map(l => graphData[l]);

    const ctx = document.getElementById('attendanceChart').getContext('2d');
    if(chartInstance) chartInstance.destroy();
    
    chartInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Present Students',
                data: counts,
                borderColor: '#4F46E5',
                backgroundColor: 'rgba(79, 70, 229, 0.1)',
                fill: true,
                tension: 0.4,
                pointBackgroundColor: '#4F46E5',
                pointRadius: 4,
                pointHoverRadius: 6
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    labels: { font: { family: "'Inter', sans-serif" } }
                }
            },
            scales: {
                y: { beginAtZero: true, ticks: { stepSize: 1 } },
                x: {
                    ticks: {
                        callback: function(val) {
                            const label = this.getLabelForValue(val);
                            return label.split('-')[2]; // Show day only
                        }
                    }
                }
            }
        }
    });
}

window.showMonthlyTable = function(type) {
    if(type === 'all') {
        document.getElementById('tableContainerAll').style.display = 'block';
        document.getElementById('tableContainerLow').style.display = 'none';
        document.getElementById('btnShowAll').className = 'btn btn-primary';
        document.getElementById('btnShowLow').className = 'btn btn-secondary';
    } else {
        document.getElementById('tableContainerAll').style.display = 'none';
        document.getElementById('tableContainerLow').style.display = 'block';
        document.getElementById('btnShowAll').className = 'btn btn-secondary';
        document.getElementById('btnShowLow').className = 'btn btn-primary';
    }
};

// ===== Holiday Calendar =====
document.getElementById('holidayMonth').value = new Date().toISOString().slice(0,7);
document.getElementById('holidayMonth').addEventListener('change', loadHolidaysCalendar);

async function loadHolidaysCalendar() {
    const m = document.getElementById('holidayMonth').value;
    if(!m) return;
    const [yearStr, monthStr] = m.split('-');
    const year = parseInt(yearStr);
    const month = parseInt(monthStr);

    const res = await fetch(`/api/settings/holidays?year=${year}&month=${month}`);
    const holidays = await res.json();
    const holidaySet = new Set(holidays);

    const grid = document.getElementById('calendarGrid');
    if(!grid) return;
    grid.innerHTML = '';

    const firstDay = new Date(year, month - 1, 1).getDay();
    const daysInMonth = new Date(year, month, 0).getDate();

    for(let i=0; i<firstDay; i++) {
        const emptyDiv = document.createElement('div');
        emptyDiv.className = 'calendar-day empty';
        grid.appendChild(emptyDiv);
    }

    for(let day=1; day<=daysInMonth; day++) {
        const dateStr = `${year}-${String(month).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
        const dayDiv = document.createElement('div');
        const isHoliday = holidaySet.has(dateStr);
        
        dayDiv.className = `calendar-day ${isHoliday ? 'holiday' : ''}`;
        dayDiv.innerText = day;
        
        dayDiv.addEventListener('click', async () => {
            if(isHoliday) {
                await fetch(`/api/settings/holidays/${dateStr}`, { method: 'DELETE' });
                showToast(`Holiday removed: ${dateStr}`, 'warning');
            } else {
                await fetch(`/api/settings/holidays/${dateStr}`, { method: 'POST' });
                showToast(`Holiday added: ${dateStr}`, 'success');
            }
            loadHolidaysCalendar();
            loadMonthly();
        });

        grid.appendChild(dayDiv);
    }
}

// ===== Init =====
loadStudents();
loadDaily();
loadMonthly();
loadHolidaysCalendar();
})();
