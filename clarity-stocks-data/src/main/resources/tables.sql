create table market
(
    identifier       varchar(10) not null
        primary key,
    name             varchar(50),
    market_type      varchar(50),
    region           varchar(50),
    city             varchar(50),
    timezone         varchar(50),
    utc_offset       integer,
    local_open_time  time,
    local_close_time time
);


create table stock
(
    symbol    varchar(10) not null
        primary key,
    exchange  varchar(10)
        references market,
    name      varchar(40),
    full_name varchar(50),
    sector    varchar(50),
    industry  varchar(50),
    city      varchar(50),
    state     varchar(50),
    country   varchar(50)
);


create table time_series_daily
(
    stock_symbol varchar(10) not null
        references stock,
    date         date        not null,
    daily_open   numeric,
    daily_high   numeric,
    daily_low    numeric,
    daily_close  numeric,
    daily_volume bigint,
    primary key (stock_symbol, date)
);


create table time_series_monthly
(
    stock_symbol   varchar(10) not null
        references stock,
    date           date        not null,
    monthly_open   numeric,
    monthly_high   numeric,
    monthly_low    numeric,
    monthly_close  numeric,
    monthly_volume bigint,
    primary key (stock_symbol, date)
);

create table company_overview
(
    symbol                        varchar(10) not null
        primary key
        references stock,
    asset_type                    varchar(25),
    name                          varchar(50),
    description                   text,
    cik                           varchar(10),
    exchange                      varchar(10),
    currency                      char(3),
    country                       varchar(50),
    sector                        varchar(50),
    industry                      varchar(255),
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
    ex_dividend_date              date
);


create table company_overview
(
    symbol                        varchar(10) not null
        primary key
        references stock,
    asset_type                    varchar(25),
    name                          varchar(50),
    description                   text,
    cik                           varchar(10),
    exchange                      varchar(10),
    currency                      char(3),
    country                       varchar(50),
    sector                        varchar(50),
    industry                      varchar(255),
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
    ex_dividend_date              date
);