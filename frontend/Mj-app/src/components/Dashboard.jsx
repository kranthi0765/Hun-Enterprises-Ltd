import React from "react";
import { Link } from "react-router-dom";

function Dashboard() {
    const navItems = [
        { label: "Dashboard", icon: "\u{1F4CA}", active: true },
        { label: "Orders", icon: "\u{1F4E6}" },
        { label: "Customers", icon: "\u{1F465}" },
        { label: "Analytics", icon: "\u{1F4C8}" },
        { label: "Settings", icon: "\u2699\uFE0F" },
    ];

    const stats = [
        { label: "Total Revenue", value: "$48,250", change: "+12.5%", icon: "\u{1F4B0}", color: "#6366f1" },
        { label: "Active Users", value: "2,847", change: "+8.1%", icon: "\u{1F465}", color: "#0ea5e9" },
        { label: "New Orders", value: "1,203", change: "+23.4%", icon: "\u{1F4E6}", color: "#10b981" },
        { label: "Conversion Rate", value: "4.6%", change: "-1.2%", icon: "\u{1F4C8}", color: "#f59e0b" },
    ];

    const recentOrders = [
        { id: "#ORD-7821", customer: "John Carter", amount: "$1,240.00", status: "Completed", date: "Jan 12, 2025" },
        { id: "#ORD-7820", customer: "Sarah Miller", amount: "$890.50", status: "Pending", date: "Jan 12, 2025" },
        { id: "#ORD-7819", customer: "David Chen", amount: "$2,150.00", status: "Completed", date: "Jan 11, 2025" },
        { id: "#ORD-7818", customer: "Emily Rodriguez", amount: "$430.25", status: "Processing", date: "Jan 11, 2025" },
        { id: "#ORD-7817", customer: "Michael Brown", amount: "$1,675.80", status: "Completed", date: "Jan 10, 2025" },
    ];

    return (
        <div className="dashboard-layout">
            <aside className="dashboard-sidebar">
                <div className="sidebar-logo">
                    <span className="logo-icon">{"\u{1F3E2}"}</span>
                    <span>MJ Hub</span>
                </div>

                <nav className="sidebar-nav" aria-label="Dashboard navigation">
                    {navItems.map((item) => (
                        <button
                            key={item.label}
                            type="button"
                            className={`sidebar-link ${item.active ? "active" : ""}`}
                            aria-pressed={item.active}
                        >
                            <span className="link-icon">{item.icon}</span>
                            <span>{item.label}</span>
                        </button>
                    ))}
                </nav>

                <div className="sidebar-footer">
                    <Link to="/login" className="logout-btn">
                        {"\u{1F6AA}"} Logout
                    </Link>
                </div>
            </aside>

            <main className="dashboard-main">
                <header className="dashboard-header">
                    <div>
                        <h1>Dashboard</h1>
                        <p>Welcome back! Here's what's happening today.</p>
                    </div>

                    <div className="header-actions">
                        <button className="header-btn" type="button" aria-label="Notifications">
                            {"\u{1F514}"}
                        </button>
                        <div className="user-avatar">MK</div>
                    </div>
                </header>

                <section className="stats-grid" aria-label="Key metrics">
                    {stats.map((stat) => (
                        <div className="stat-card" key={stat.label}>
                            <div className="stat-icon" style={{ background: `${stat.color}15`, color: stat.color }}>
                                {stat.icon}
                            </div>
                            <div className="stat-info">
                                <span className="stat-label">{stat.label}</span>
                                <span className="stat-value">{stat.value}</span>
                                <span className={`stat-change ${stat.change.startsWith("-") ? "negative" : "positive"}`}>
                                    {stat.change} vs last month
                                </span>
                            </div>
                        </div>
                    ))}
                </section>

                <section className="dashboard-grid">
                    <div className="card chart-card">
                        <div className="card-header">
                            <h3>Revenue Overview</h3>
                            <span className="card-badge">Last 6 months</span>
                        </div>
                        <div className="chart-placeholder" aria-label="Revenue chart">
                            <div className="bar-chart">
                                {[45, 62, 38, 78, 55, 90].map((height, index) => (
                                    <div className="bar" key={index} style={{ height: `${height}%` }}>
                                        <span className="bar-value">{height}%</span>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>

                    <div className="card">
                        <div className="card-header">
                            <h3>Recent Orders</h3>
                            <a href="#recent-orders" className="view-all">
                                View all {"\u2192"}
                            </a>
                        </div>
                        <div className="orders-table">
                            <table id="recent-orders">
                                <thead>
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Customer</th>
                                        <th>Amount</th>
                                        <th>Status</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {recentOrders.map((order) => (
                                        <tr key={order.id}>
                                            <td>{order.id}</td>
                                            <td>{order.customer}</td>
                                            <td>{order.amount}</td>
                                            <td>
                                                <span className={`status-badge ${order.status.toLowerCase()}`}>
                                                    {order.status}
                                                </span>
                                            </td>
                                            <td>{order.date}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </main>
        </div>
    );
}

export default Dashboard;
