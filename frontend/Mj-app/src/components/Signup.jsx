import React, { useState } from "react";
import FormInput from "./FormInput";

function Signup() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Signup:", { username, email, password });
        // Later: send to backend API
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            <h2>Sign Up</h2>
            <FormInput label="Username" type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            <FormInput label="Email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <FormInput label="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <button type="submit" className="btn">Sign Up</button>
        </form>
    );
}

export default Signup;
