import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import FormInput from "./FormInput";

function Signup() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [message, setMessage] = useState("");
    const [messageType, setMessageType] = useState("");
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            setMessageType("error");
            setMessage("Passwords do not match.");
            return;
        }
        setLoading(true);
        setMessage("");
        try {
            const response = await fetch("/api/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username, email, password }),
            });
            const data = await response.text();
            if (response.ok) {
                setMessageType("success");
                setMessage(data);
                setUsername("");
                setEmail("");
                setPassword("");
                setConfirmPassword("");
                setTimeout(() => navigate("/login"), 1500);
            } else {
                setMessageType("error");
                setMessage(data);
            }
        } catch (error) {
            console.error("Error during signup:", error);
            setMessageType("error");
            setMessage("An error occurred during signup. Please try again later.");
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
                        <h1>Create Your Account</h1>
                        <p>Join MJ Hub Technologies and unlock powerful tools for your business.</p>
                        <div className="brand-features">
                            <div className="brand-feature">
                                <span className="feature-icon">{"\u{1F680}"}</span>
                                <span>Get started in minutes</span>
                            </div>
                            <div className="brand-feature">
                                <span className="feature-icon">{"\u{1F6E1}\uFE0F"}</span>
                                <span>Your data is protected</span>
                            </div>
                            <div className="brand-feature">
                                <span className="feature-icon">{"\u{1F91D}"}</span>
                                <span>Dedicated support team</span>
                            </div>
                        </div>
                    </div>
                    <div className="brand-footer">{"\u00A9"} 2025 MJ Hub Technologies. All rights reserved.</div>
                </div>

                <div className="auth-form-side">
                    <h2>Sign Up</h2>
                    <p className="auth-subtitle">Create your free account to get started</p>

                    <form onSubmit={handleSubmit} className="form-container">
                        <FormInput
                            label="Username"
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            icon={"\u{1F464}"}
                            placeholder="Enter your username"
                            autoComplete="username"
                        />
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
                            placeholder="Create a password"
                            autoComplete="new-password"
                        />
                        <FormInput
                            label="Confirm Password"
                            type="password"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            icon={"\u{1F512}"}
                            placeholder="Confirm your password"
                            autoComplete="new-password"
                        />
                        <button type="submit" className="btn" disabled={loading}>
                            {loading ? "Creating account..." : "Create Account"}
                        </button>
                    </form>

                    {message && <div className={`form-message ${messageType}`}>{message}</div>}

                    <div className="divider">or sign up with</div>

                    <div className="social-buttons">
                        <button className="social-btn" type="button">{"\u{1F535}"} Google</button>
                        <button className="social-btn" type="button">{"\u26AB"} GitHub</button>
                    </div>

                    <p className="auth-switch">
                        Already have an account? <Link to="/login">Sign in</Link>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default Signup;
