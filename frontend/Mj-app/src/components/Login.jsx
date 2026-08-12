import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import FormInput from "./FormInput";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [messageType, setMessageType] = useState("");
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setMessage("");
        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });
            const data = await response.text();
            if (response.ok) {
                setMessageType("success");
                setMessage(data);
                setTimeout(() => navigate("/dashboard"), 1200);
            } else {
                setMessageType("error");
                setMessage(data);
            }
        } catch (error) {
            console.error("Error during login:", error);
            setMessageType("error");
            setMessage("An error occurred during login. Please try again later.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="auth-backdrop">
            <div className="auth-card">
                <div className="auth-brand">
                    <div className="brand-logo">
                        <span className="logo-icon">{"\u{1F3E2}"}</span>
                        <span>MJ Hub Technologies</span>
                    </div>
                    <div className="brand-content">
                        <h1>Welcome Back!</h1>
                        <p>Sign in to access your enterprise dashboard and manage your business operations.</p>
                        <div className="brand-features">
                            <div className="brand-feature">
                                <span className="feature-icon">{"\u{1F4CA}"}</span>
                                <span>Real-time analytics &amp; insights</span>
                            </div>
                            <div className="brand-feature">
                                <span className="feature-icon">{"\u{1F512}"}</span>
                                <span>Secure enterprise-grade access</span>
                            </div>
                            <div className="brand-feature">
                                <span className="feature-icon">{"\u26A1"}</span>
                                <span>Streamlined business workflows</span>
                            </div>
                        </div>
                    </div>
                    <div className="brand-footer">{"\u00A9"} 2025 MJ Hub Technologies. All rights reserved.</div>
                </div>

                <div className="auth-form-side">
                    <h2>Sign In</h2>
                    <p className="auth-subtitle">Enter your credentials to access your account</p>

                    <form onSubmit={handleSubmit} className="form-container">
                        <FormInput
                            label="Email Address"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            icon={"\u2709\uFE0F"}
                            placeholder="you@company.com"
                            autoComplete="email"
                        />
                        <FormInput
                            label="Password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            icon={"\u{1F512}"}
                            placeholder="Enter your password"
                            autoComplete="current-password"
                        />
                        <button type="submit" className="btn" disabled={loading}>
                            {loading ? "Signing in..." : "Sign In"}
                        </button>
                    </form>

                    {message && <div className={`form-message ${messageType}`}>{message}</div>}

                    <div className="divider">or continue with</div>

                    <div className="social-buttons">
                        <button className="social-btn" type="button">{"\u{1F535}"} Google</button>
                        <button className="social-btn" type="button">{"\u26AB"} GitHub</button>
                    </div>

                    <p className="auth-switch">
                        Don't have an account? <Link to="/signup">Create one</Link>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default Login;
