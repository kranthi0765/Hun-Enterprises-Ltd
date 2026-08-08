import React, { useState } from "react";
import FormInput from "./FormInput";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Login:", { email, password });
        // Later: send to backend API
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            <h2>Login</h2>
            <FormInput label="Email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <FormInput label="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <button type="submit" className="btn">Login</button>
        </form>
    );
}

export default Login;
