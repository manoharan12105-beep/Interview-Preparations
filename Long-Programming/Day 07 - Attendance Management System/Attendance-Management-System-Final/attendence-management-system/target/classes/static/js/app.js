// Common App Logic

// ===== Toast Notification System =====
window.showToast = function(message, type = 'success') {
    let container = document.getElementById('toastContainer');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toastContainer';
        container.className = 'toast-container';
        document.body.appendChild(container);
    }
    
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    
    const icons = { success: '✅', error: '❌', warning: '⚠️' };
    toast.innerHTML = `<span class="toast-icon">${icons[type] || '💬'}</span><span>${message}</span>`;
    
    container.appendChild(toast);
    
    setTimeout(() => {
        toast.classList.add('toast-out');
        setTimeout(() => toast.remove(), 300);
    }, 3000);
};

// ===== Utility: Button loading state =====
window.setBtnLoading = function(btn, loading) {
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
        if (textEl) textEl.innerText = btn.dataset.originalText || 'Submit';
    }
};

// ===== Password Visibility Toggle =====
window.togglePassword = function() {
    const input = document.getElementById('password');
    const eyeIcon = document.getElementById('eyeIcon');
    const eyeOffIcon = document.getElementById('eyeOffIcon');
    if (input && input.type === 'password') {
        input.type = 'text';
        if (eyeIcon) eyeIcon.style.display = 'none';
        if (eyeOffIcon) eyeOffIcon.style.display = 'block';
    } else if (input) {
        input.type = 'password';
        if (eyeIcon) eyeIcon.style.display = 'block';
        if (eyeOffIcon) eyeOffIcon.style.display = 'none';
    }
};

function logout() {
    sessionStorage.removeItem('user');
    window.location.href = '/index.html';
}

// Login Logic
const loginForm = document.getElementById('loginForm');
if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const btn = document.getElementById('loginBtn');
        const err = document.getElementById('loginError');
        
        setBtnLoading(btn, true);
        err.innerText = '';
        
        try {
            const res = await fetch('/api/users/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });
            const data = await res.json();
            if(res.ok) {
                sessionStorage.setItem('user', JSON.stringify(data));
                showToast('Login successful!', 'success');
                setTimeout(() => {
                    if (data.role === 'ROLE_ADMIN') {
                        window.location.href = '/admin.html';
                    } else {
                        window.location.href = '/student.html';
                    }
                }, 500);
                return; // don't reset button if navigating
            } else {
                err.innerText = data.error || 'Login failed';
                showToast(data.error || 'Login failed', 'error');
            }
        } catch (error) {
            err.innerText = 'Server error. Please try again.';
            showToast('Server error', 'error');
        }
        setBtnLoading(btn, false);
    });
}

// Handle redirect errors from OAuth2
const urlParams = new URLSearchParams(window.location.search);
if (urlParams.get('error')) {
    const err = document.getElementById('loginError');
    if (err) {
        if (urlParams.get('error') === 'UserNotFound') {
            err.innerText = 'Account not registered in the system.';
            setTimeout(() => showToast('Account not registered', 'error'), 100);
        } else {
            err.innerText = 'Authentication failed. Please try again.';
            setTimeout(() => showToast('Authentication failed', 'error'), 100);
        }
    }
}
