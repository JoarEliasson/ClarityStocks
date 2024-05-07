create table market
(
    identifier       varChar(10) primary key,

    name             varChar(50),
    market_type      varChar(50),
    region           varChar(50),
    city             varChar(50),
    timezone         varChar(50),
    utc_offset       integer,
    local_open_time  time,
    local_close_time time
);

create table stock
(
    symbol    varChar(10) primary key,

    exchange  varChar(10),
    name      varChar(40),
    full_name varChar(3),
    sector    varChar(50),
    industry  varChar(50),
    city      varChar(50),
    state     varChar(50),
    country   varChar(50),

    foreign key (exchange) references market (identifier)
);


create table time_series_daily
(
    stock_symbol varChar(10),
    date         date,
    daily_open   numeric,
    daily_high   numeric,
    daily_low    numeric,
    daily_close  numeric,
    daily_volume bigInt,

    foreign key (stock_symbol) references stock (symbol),
    primary key (stock_symbol, date)
);

create table company_overview
(
    symbol                        varchar(10),
    asset_type                    varchar(25),
    name                          varchar(50),
    description                   text,
    cik                           varchar(10),
    exchange                      varchar(10),
    currency                      char(3),
    country                       varchar(50),
    sector                        varchar(30),
    industry                      varchar(30),
    address                       varchar(50),
    fiscal_year_end               varchar(20),
    latest_quarter                date,
    market_capitalization         bigint,
    ebitda                        numeric,
    pe_ratio                      numeric,
    peg_ratio                     numeric,
    book_value                    numeric,
    dividend_per_share            numeric,
    dividend_yield                numeric,
    eps                           numeric,
    revenue_per_share_ttm         numeric,
    profit_margin                 numeric,
    operating_margin_ttm          numeric,
    return_on_assets_ttm          numeric,
    return_on_equity_ttm          numeric,
    revenue_ttm                   bigint,
    gross_profit_ttm              bigint,
    diluted_eps_ttm               numeric,
    quarterly_earnings_growth_yoy numeric,
    quarterly_revenue_growth_yoy  numeric,
    analyst_target_price          numeric,
    analyst_rating_strong_buy     integer,
    analyst_rating_buy            integer,
    analyst_rating_hold           integer,
    analyst_rating_sell           integer,
    analyst_rating_strong_sell    integer,
    trailing_pe                   numeric,
    forward_pe                    numeric,
    price_to_sales_ratio_ttm      numeric,
    price_to_book_ratio           numeric,
    ev_to_revenue                 numeric,
    ev_to_ebitda                  numeric,
    beta                          numeric,
    week_52_high                  numeric,
    week_52_low                   numeric,
    moving_average_50             numeric,
    moving_average_200            numeric,
    shares_outstanding            bigint,
    dividend_date                 date,
    ex_dividend_date              date,

    foreign key (symbol) references stock (symbol),
    primary key (symbol)
);

create table income_statement
(
    symbol                                varchar(10),
    fiscal_date_ending                    date,
    reported_currency                     char(3),
    gross_profit                          bigint,
    total_revenue                         bigint,
    cost_of_revenue                       bigint,
    cost_of_goods_and_services_sold       bigint,
    operating_income                      bigint,
    selling_general_and_administrative    bigint,
    research_and_development              bigint,
    operating_expenses                    bigint,
    investment_income_net                 bigint,
    net_interest_income                   bigint,
    interest_income                       bigint,
    interest_expense                      bigint,
    non_interest_income                   bigint,
    other_non_operating_income            bigint,
    depreciation                          bigint,
    depreciation_and_amortization         bigint,
    income_before_tax                     bigint,
    income_tax_expense                    bigint,
    interest_and_debt_expense             bigint,
    net_income_from_continuing_operations bigint,
    comprehensive_income_net_of_tax       bigint,
    ebit                                  bigint,
    ebitda                                bigint,
    net_income                            bigint,

    primary key (symbol, fiscal_date_ending),
    foreign key (symbol) references stock (symbol)
);