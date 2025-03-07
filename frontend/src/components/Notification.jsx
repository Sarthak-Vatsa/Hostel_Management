import React, { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "./ui/table";
import { Avatar, AvatarImage } from "./ui/avatar";
import { Popover, PopoverContent, PopoverTrigger } from "./ui/popover";
import { Edit2, MoreHorizontal } from "lucide-react";
import { setAllNotices } from "@/redux/authSlice";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";

function Notification() {
  const dispatch = useDispatch();
  const { allNotices } = useSelector((store) => store.auth);
  useEffect(() => {
    const fetchNotices = async () => {
      try {
        const resp = await axios.get(
          "https://backend-hostel1.onrender.com/students/viewNotices",
          {
            withCredentials: true, // Ensures cookies are sent
            headers: {
              Accept: "application/json",
            },
          }
        );
        console.log(resp.data);
        dispatch(setAllNotices(resp.data));
        // console.log(notification);
      } catch (error) {
        console.error("Error fetching notices:", error);
      }
    };
    fetchNotices();
  }, []);

  return (
    // <div className="p-4 mt-2 mx-8 rounded-md border-2 border-black bg-white">
    //   <p className="font-semibold text-lg">

    //   </p>
    // </div>
    <div className="max-w-6xl mx-auto my-10">
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="text-semibold text-lg">Notification</TableHead>
          </TableRow>
        </TableHeader>
            </Table>
        <div className="max-h-64 overflow-y-auto">
          <Table>
          {/* <TableCaption>A List of your recent Notification</TableCaption> */}
          <TableBody>
            {allNotices?.map((notice) => (
              <tr>
                <TableCell>{notice.content}</TableCell>
                <TableCell>{notice.date}</TableCell>
                {/* <TableCell className="text-right cursor-pointer">
                  <Popover>
                    <PopoverTrigger>
                      <MoreHorizontal />
                    </PopoverTrigger>
                    <PopoverContent className="w-32">
                      <div
                        // onClick={() =>
                          //   navigate(`/admin/companies/${company._id}`)
                          // }
                          className="flex items-center gap-2 w-fit cursor-pointer"
                          >
                        <Edit2 className="w-4" />
                        <span>Edit</span>
                      </div>
                    </PopoverContent>
                  </Popover>
                </TableCell> */}
              </tr>
            ))}
          </TableBody>
            </Table>
        </div>
    </div>
  );
}

export default Notification;
