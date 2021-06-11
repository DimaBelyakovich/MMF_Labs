# -*- coding: utf-8 -*-

import collections
import sys

# Start 1st block
ID, FORENAME, MIDDLENAME, SURNAME, DEPARTMENT = range(5)
# End 1st block

User = collections.namedtuple("User",
            "username forename middlename surname id")
# End 2d block

def main():
    if len(sys.argv) == 1 or sys.argv[1] in {"-h", "--help"}:
        print("usage: {0} file1 [file2 [... fileN]]".format(
              sys.argv[0]))
        sys.exit()

    usernames = set()
    users = {}
    for filename in sys.argv[1:]:
        with open(filename, encoding="utf8") as file:
            for line in file:
                line = line.rstrip()
                if line:
                    user = process_line(line, usernames)
                    users[(user.surname.lower(), user.forename.lower(),
                            user.id)] = user
    print_users(users)


def process_line(line, usernames):
    fields = line.split(":")
    username = generate_username(fields, usernames)
    user = User(username, fields[FORENAME], fields[MIDDLENAME],
                fields[SURNAME], fields[ID])
    return user


def generate_username(fields, usernames):
    username = ((fields[FORENAME][0] + fields[MIDDLENAME][:1] +
                 fields[SURNAME]).replace("-", "").replace("'", ""))
    username = original_name = username[:8].lower()
    count = 1
    while username in usernames:
        username = "{0}{1}".format(original_name, count)
        count += 1
    usernames.add(username)
    return username


def print_users(users):
    namewidth = 17
    usernamewidth = 9
    ellipsiswidth = 0
    page_lines = 64

    page_header = get_page_header()

    sorted_users = sorted(users)

    for i, (lcol, rcol) in enumerate(zip(sorted_users[::2], sorted_users[1::2])):
        if i % page_lines == 0:
            print("\f" + page_header)

        right_entry = "{}{}".format("  ", get_user_entry(users[rcol])) if rcol is not None else ""
        print(get_user_entry(users[lcol]) + right_entry)

def get_user_entry(user, namewidth=17, usernamewidth=9, ellipsiswidth=0):
    initial = ""
    if user.middlename:
        initial = " " + user.middlename[0]
    name = "{0.surname}, {0.forename}{1}".format(user, initial)
    return "{0:.<{nw}.{np}} ({1.id:4}) {1.username:{uw}}".format(
        name, user, nw=namewidth, uw=usernamewidth, np=namewidth - ellipsiswidth)


def get_page_header(namewidth=17, usernamewidth=9):
    header = "{0:<{nw}} {1:^6} {2:{uw}}".format(
        "Name", "ID", "Username", nw=namewidth, uw=usernamewidth)
    headers_delim = "  "
    two_headers = header + headers_delim + header
    hr = "{0:-<{nw}} {0:-<6} {0:-<{uw}}".format(
        "", nw=namewidth, uw=usernamewidth)
    two_hr = hr + headers_delim + hr
    return two_headers + "\n" + two_hr

main()
